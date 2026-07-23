package com.genai.schemamapping.ingestion;

import com.genai.schemamapping.entity.SourceColumn;
import com.genai.schemamapping.entity.SourceTable;
import com.genai.schemamapping.ingestion.jdbc.JdbcConnectionService;
import com.genai.schemamapping.repository.SourceColumnRepository;
import com.genai.schemamapping.repository.SourceTableRepository;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Set;

@Service
public class MetadataExtractionService {


    private final JdbcConnectionService jdbcConnectionService;

    private final SourceTableRepository sourceTableRepository;

    private final SourceColumnRepository sourceColumnRepository;


    private final Set<String> ignoredTables = Set.of(
            "SOURCE_TABLE",
            "SOURCE_COLUMN",
            "TARGET_COLUMN",
            "MAPPING_RESULT"
    );


    public MetadataExtractionService(
            JdbcConnectionService jdbcConnectionService,
            SourceTableRepository sourceTableRepository,
            SourceColumnRepository sourceColumnRepository) {

        this.jdbcConnectionService = jdbcConnectionService;
        this.sourceTableRepository = sourceTableRepository;
        this.sourceColumnRepository = sourceColumnRepository;
    }



    public void extractTables() {


        try {


            Connection connection =
                    jdbcConnectionService.getConnection(
                            "jdbc:h2:file:./data/sourcedb",
                            "sa",
                            ""
                    );


            System.out.println("CONNECTED DATABASE");
            System.out.println(connection.getMetaData().getURL());


            DatabaseMetaData metadata =
                    connection.getMetaData();



            ResultSet tables =
                    metadata.getTables(
                            null,
                            "PUBLIC",
                            "%",
                            new String[]{"TABLE"}
                    );



            while(tables.next()) {


                String tableName =
                        tables.getString("TABLE_NAME");


                if(ignoredTables.contains(tableName)) {
                    continue;
                }



                System.out.println(
                        "\nTABLE FOUND : " + tableName
                );



                SourceTable sourceTable =
                        new SourceTable();


                sourceTable.setTableName(tableName);

                sourceTable.setDescription(
                        "Extracted from source ERP database"
                );


                sourceTableRepository.save(sourceTable);



                ResultSet columns =
                        metadata.getColumns(
                                null,
                                "PUBLIC",
                                tableName,
                                "%"
                        );



                while(columns.next()) {


                    String columnName =
                            columns.getString("COLUMN_NAME");


                    String dataType =
                            columns.getString("TYPE_NAME");



                    String sampleValue =
                            extractSampleValue(
                                    connection,
                                    tableName,
                                    columnName
                            );



                    SourceColumn sourceColumn =
                            new SourceColumn();


                    sourceColumn.setColumnName(columnName);

                    sourceColumn.setDataType(dataType);

                    sourceColumn.setBusinessDescription(
                            "Extracted column metadata"
                    );


                    sourceColumn.setSampleValue(
                            sampleValue
                    );


                    sourceColumn.setSourceTable(
                            sourceTable
                    );


                    sourceColumnRepository.save(sourceColumn);



                    System.out.println(
                            "COLUMN : "
                            + columnName
                            + 
                            " TYPE : "
                            + dataType
                            +
                            " SAMPLE : "
                            + sampleValue
                    );

                }


                columns.close();

            }


            tables.close();

            connection.close();


            System.out.println(
                    "Sample data extraction completed successfully!"
            );


        }
        catch(Exception e){

            e.printStackTrace();

        }

    }



    private String extractSampleValue(
            Connection connection,
            String tableName,
            String columnName
    ) {


        StringBuilder values =
                new StringBuilder();


        try {


            String query =
                    "SELECT "
                    + columnName +
                    " FROM "
                    + tableName +
                    " LIMIT 5";


            Statement statement =
                    connection.createStatement();


            ResultSet resultSet =
                    statement.executeQuery(query);



            while(resultSet.next()) {


                Object value =
                        resultSet.getObject(1);


                if(value != null) {

                    if(values.length() > 0)
                        values.append(",");


                    values.append(value);

                }

            }


            resultSet.close();

            statement.close();


        }
        catch(Exception e){

            System.out.println(
                    "Sample extraction failed for "
                    + columnName
            );
        }
        return values.toString();
    }
public long countTables(){

    return sourceTableRepository.count();

}
}