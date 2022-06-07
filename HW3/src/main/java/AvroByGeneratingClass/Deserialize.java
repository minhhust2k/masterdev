package AvroByGeneratingClass;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;
import src.main.java.AvroByGeneratingClass.FootballClub;

import java.io.File;
import java.io.IOException;

public class Deserialize {
    public static void main(String args[]) throws IOException {
        DatumReader<FootballClub> empDatumReader = new SpecificDatumReader<FootballClub>(FootballClub.class);

        //Instantiating DataFileReader
        DataFileReader<FootballClub> dataFileReader = new DataFileReader<FootballClub>(new File("src/main/java/AvroByGeneratingClass/EPL.avro"), empDatumReader);
        FootballClub footballClub = null;
        System.out.println("src/main/java/AvroByGeneratingClass/EPL.avro");

        while(dataFileReader.hasNext()){
            footballClub = dataFileReader.next(footballClub);
            System.out.println(footballClub);
        }

        System.out.println("Data successfully deserialized !");
        dataFileReader.close();
    }
}
