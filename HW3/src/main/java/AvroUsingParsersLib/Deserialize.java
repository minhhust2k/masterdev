package AvroUsingParsersLib;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;

import java.io.File;
import java.io.IOException;

public class Deserialize {
    public static void main(String args[]) throws IOException {

        /* Read schema definition and create schema object */
        Schema schema = new Schema.Parser().parse(new File("src/main/java/AvroUsingParsersLib/FootballClub.avsc"));

        // De-serialize employee from disk
        File file = new File("src/main/java/AvroUsingParsersLib/EPL.avro");

        // Deserialize users from disk
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);

        GenericRecord club = null;
        System.out.println("Deserializing from src/main/java/AvroUsingParsersLib/EPL.avro");
        while (dataFileReader.hasNext()) {
            club = dataFileReader.next(club);
            System.out.println(club);
        }
        System.out.println("Data successfully deserialized !");
        dataFileReader.close();
    }
}
