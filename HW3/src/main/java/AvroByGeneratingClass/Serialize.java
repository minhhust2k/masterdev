package AvroByGeneratingClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import src.main.java.AvroByGeneratingClass.FootballClub;
import src.main.java.AvroByGeneratingClass.Player;
public class Serialize {
    public static void main(String args[]) throws IOException {

        /* Define object */
        FootballClub MUFC = new FootballClub();
        List<Player> MUteam = new ArrayList<>();

        Player player1 = new Player();
        player1.setAge(37);
        player1.setName("Ronaldo");
        player1.setSalary(500000);

        Player player2 = new Player();
        player2.setAge(29);
        player2.setName("Maguire");
        player2.setSalary(0);

        Player player3 = new Player();
        player3.setAge(32);
        player3.setName("De Gea");
        player3.setSalary(600000);

        MUteam.add(player1);
        MUteam.add(player2);
        MUteam.add(player3);

        MUFC.setName("Manchester United");
        MUFC.setManager("Erik ten Hag");
        MUFC.setStadium("Old Trafford");
        MUFC.setTrophies(20);
        MUFC.setPhone(1234567890L);
        MUFC.setPlayerList(MUteam);

        FootballClub LVFC = new FootballClub();
        List<Player> LVteam = new ArrayList<>();

        Player player4 = new Player();
        player4.setAge(30);
        player4.setName("Salah");
        player4.setSalary(450000);

        Player player5 = new Player();
        player5.setAge(30);
        player5.setName("Mane");
        player5.setSalary(450000);

        Player player6 = new Player();
        player6.setAge(31);
        player6.setName("Van Dijk");
        player6.setSalary(400000);

        LVteam.add(player4);
        LVteam.add(player5);
        LVteam.add(player6);

        LVFC.setName("Liverpool");
        LVFC.setManager("Jurgen Klopp");
        LVFC.setStadium("Anfield");
        LVFC.setTrophies(19);
        LVFC.setPhone(1234567890L);
        LVFC.setPlayerList(LVteam);

        /* Convert java object into in-memory serialized format */
        DatumWriter<FootballClub> clubDatumWriter = new SpecificDatumWriter<FootballClub>(FootballClub.class);
        DataFileWriter<FootballClub> clubFileWriter = new DataFileWriter<FootballClub>(clubDatumWriter);

        /* Create avro file*/
        clubFileWriter.create(MUFC.getSchema(), new File("src/main/java/AvroByGeneratingClass/EPL.avro"));
        clubFileWriter.append(MUFC);
        clubFileWriter.append(LVFC);

        System.out.println("Data successfully serialized at src/main/java/AvroByGeneratingClass/EPL.avro !");
        clubFileWriter.close();
    }
}
