package AvroUsingParsersLib;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;

public class Serialize {
    public static void main(String args[]) throws IOException {
        /* Read schema definition and create schema object */
        Schema schema = new Schema.Parser().parse(new File("src/main/java/AvroUsingParsersLib/FootballClub.avsc"));

        /* Convert java object into in-memory serialized format */
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
        dataFileWriter.create(schema, new File("src/main/java/AvroUsingParsersLib/EPL.avro"));

        /* Use the schema and create an employee object */
        GenericRecord MUFC = new GenericData.Record(schema);

        /* Define */
        MUFC.put("Name", "Manchester United");
        MUFC.put("Manager", "Erik ten Hag");
        MUFC.put("Stadium", "Old Trafford");
        MUFC.put("Trophies",20);
        MUFC.put("Phone", 1234567890L);
        MUFC.put("isChampion", false);

        GenericRecord MCFC = new GenericData.Record(schema);
        MCFC.put("Name", "Manchester City");
        MCFC.put("Manager", "Pep Guardiola");
        MCFC.put("Stadium", "Etihad");
        MCFC.put("Trophies",6);
        MCFC.put("Phone", 1234567890L);
        MCFC.put("isChampion", true);

        GenericRecord LVFC = new GenericData.Record(schema);
        LVFC.put("Name", "Liverpool");
        LVFC.put("Manager", "Jurgen Klopp");
        LVFC.put("Stadium", "Anfield");
        LVFC.put("Trophies",19);
        LVFC.put("Phone", 1234567890L);
        LVFC.put("isChampion", false);

        GenericRecord ARS = new GenericData.Record(schema);
        ARS.put("Name", "Arsenal");
        ARS.put("Manager", "Mikel Arteta");
        ARS.put("Stadium", "Emirates");
        ARS.put("Trophies",13);
        ARS.put("Phone", 1234567890L);
        ARS.put("isChampion", false);

        dataFileWriter.append(MUFC);
        dataFileWriter.append(MCFC);
        dataFileWriter.append(LVFC);
        dataFileWriter.append(ARS);

        System.out.println("Data successfully serialized at src/main/java/AvroUsingParsersLib/EPL.avro !");
        dataFileWriter.close();
    }
}

