package com.genai.schemamapping.ingestion;

import com.genai.schemamapping.entity.SourceColumn;
import com.genai.schemamapping.entity.SourceTable;
import com.genai.schemamapping.ingestion.jdbc.JdbcConnectionService;
import com.genai.schemamapping.repository.SourceColumnRepository;
import com.genai.schemamapping.repository.SourceTableRepository;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
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
                            "jdbc:h2:mem:sourcedb",
                            "sa",
                            ""
                    );


            System.out.println("CONNECTED DATABASE");
            System.out.println(
                    connection.getMetaData().getURL()
            );


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
                        "\nTABLE FOUND : "
                                + tableName
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
                            columns.getString(
                                    "COLUMN_NAME"
                            );


                    String dataType =
                            columns.getString(
                                    "TYPE_NAME"
                            );



                    System.out.println(
                            "COLUMN : "
                                    + columnName
                                    + " TYPE : "
                                    + dataType
                    );



                    SourceColumn sourceColumn =
                            new SourceColumn();


                    sourceColumn.setColumnName(
                            columnName
                    );


                    sourceColumn.setDataType(
                            dataType
                    );


                    sourceColumn.setBusinessDescription(
                            "Extracted column metadata"
                    );


                    sourceColumn.setSourceTable(
                            sourceTable
                    );


                    sourceColumnRepository.save(
                            sourceColumn
                    );

                }


                columns.close();

            }


            tables.close();

            connection.close();


            System.out.println(
                    "\nMetadata extraction completed successfully!"
            );


        }
        catch(Exception e){

            e.printStackTrace();

        }

    }

}
