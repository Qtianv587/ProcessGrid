import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws IOException {
//        testHashMap();
//        getClosestGrid();
//        genGeoFile();
        double x = -1;
        System.out.println(Math.toDegrees(Math.atan(x)));
    }

    public static void testHashMap() {
        HashMap<Integer, ArrayList<String>> tmp = new HashMap<>();
        ArrayList<String> als = new ArrayList<>();
        als.add("1");
        als.add("1");
        als.add("1");
        als.add("1");
        als.add("1");
        als.add("1");
        als.add("1");
        tmp.put(0, als);
        System.out.println(tmp.get(0).size());
        tmp.get(0).add("111111");
        System.out.println(tmp.get(0).size());
    }

    public static void getClosestGrid() {
        SAXReader reader = new SAXReader();
        ArrayList<String[]> alsa = new ArrayList<>();
        try {
            Document document = reader.read(new File("C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\锦江主河道.kml"));
            Element head=document.getRootElement();
            Element folder = head.element("Document");
            alsa = GenId.getNodes(folder);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        double flag = 999999.9999;
        String res = "";
        for (String[] strings: alsa) {
            String location = "104.05374929308887,30.49752354310688";

            for (int i = 0; i < strings.length; i++) {
                double d;
//                System.out.println(strings[i]);
                if ((d = getDistance(location, strings[i])) <= flag) {
                    flag = d;
                    res = strings[i];
                }
            }
        }

        System.out.println("[" + res + "],");
        for (String[] strings: alsa) {
            for (String s: strings) {
                if (s.equals(res)) {
                    System.out.println("[" + strings[0] + "],[" + strings[1] + "],[" + strings[2] + "],[" + strings[3] + "],");
                }
            }
        }
    }

    public static double getDistance(String location1, String location2) {
        double lng1 = Double.parseDouble(location1.split(",")[0]);
        double lat1 = Double.parseDouble(location1.split(",")[1]);

        double lng2 = Double.parseDouble(location2.split(",")[0]);
        double lat2 = Double.parseDouble(location2.split(",")[1]);

        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        return s;
    }

    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    private static double EARTH_RADIUS = 6371.393;

    public static void genGeoFile() throws IOException {
        String pathA = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段A网格数据.txt";
        String pathB = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段B网格数据.txt";
        String pathC = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段C网格数据.txt";
        String pathD = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段D网格数据.txt";
        String pathE = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段E网格数据.txt";
        String pathF = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段F网格数据.txt";
        String pathG = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段G网格数据.txt";
        String pathH = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段H网格数据.txt";
        String pathI = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段I网格数据.txt";
        String pathJ = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段J网格数据.txt";
        String pathK = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段K网格数据.txt";
        String pathL = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段L网格数据.txt";
        String pathM = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段M网格数据.txt";
        String pathN = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段N网格数据.txt";
        String pathO = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段O网格数据.txt";
        String pathOutA = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\A河段网格数据.json";
        String pathOutB = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\B河段网格数据.json";
        String pathOutC = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\C河段网格数据.json";
        String pathOutD = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\D河段网格数据.json";
        String pathOutE = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\E河段网格数据.json";
        String pathOutF = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\F河段网格数据.json";
        String pathOutG = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\G河段网格数据.json";
        String pathOutH = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\H河段网格数据.json";
        String pathOutI = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\I河段网格数据.json";
        String pathOutJ = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\J河段网格数据.json";
        String pathOutK = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\K河段网格数据.json";
        String pathOutL = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\L河段网格数据.json";
        String pathOutM = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\M河段网格数据.json";
        String pathOutN = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\N河段网格数据.json";
        String pathOutO = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\O河段网格数据.json";
        genGeoJsonFile(pathA, pathOutA,"A", "3", "1.5", "129.5");
        genGeoJsonFile(pathB, pathOutB,"B", "3", "1.5", "99.6");
        genGeoJsonFile(pathC, pathOutC,"C", "4", "1.5", "43.9");
        genGeoJsonFile(pathD, pathOutD,"D", "4", "1.5", "27.2");
        genGeoJsonFile(pathE, pathOutE,"E", "3", "1", "10");
        genGeoJsonFile(pathF, pathOutF,"F", "4", "1", "56.8");
        genGeoJsonFile(pathG, pathOutG,"G", "4", "1", "73.5");
        genGeoJsonFile(pathH, pathOutH,"H", "4", "1", "46");
        genGeoJsonFile(pathI, pathOutI,"I", "4", "1", "27.5");
        genGeoJsonFile(pathJ, pathOutJ,"J", "4", "1", "54.7");
        genGeoJsonFile(pathK, pathOutK,"K", "3", "1", "19.9");
        genGeoJsonFile(pathL, pathOutL,"L", "4", "1", "55.8");
        genGeoJsonFile(pathM, pathOutM,"M", "4", "1.5", "16.7");
        genGeoJsonFile(pathN, pathOutN,"N", "4", "1", "100.7");
        genGeoJsonFile(pathO, pathOutO,"O", "5", "1", "120.6");
    }

    public static void genGeoJsonFile(String path, String pathOut, String riverNo, String level, String river_speed, String flow) throws IOException {

        File file = new File(path);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(isr);
        ArrayList<String> als = new ArrayList<>();
        String line;
        System.setOut(new PrintStream(new File(pathOut)));
        System.out.println("{\"type\":\"GeometryCollection\", \"geometries\": [");
        while ((line = br.readLine()) != null) {
            String[] strings = line.split("(\\s+\\s+)");
            als.add(strings[4]);
//            String[] coord = strings[4].split(" ");
//            System.out.print("{\"type\":\"Polygon\",\"coordinates\":[[");
//            System.out.print("[" + coord[0] + "],[" + coord[1] + "],[" + coord[2] + "],[" + coord[3] + "],[" + coord[0] + "]");
//            System.out.println("]]},");
        }

        for (int i = 0; i < als.size(); i++) {
            String[] coord = als.get(i).split(" ");
            System.out.print("{\"type\":\"Polygon\",\"coordinates\":[[");
            System.out.print("[" + coord[0] + "],[" + coord[1] + "],[" + coord[2] + "],[" + coord[3] + "],[" + coord[0] + "]");
            if (i == als.size() - 1)
                System.out.println("]]}");
            else System.out.println("]]},");
        }
        
        System.out.println("],\n" +
                "\"properties\": {\n" +
                "  \"river_no\": \"" + riverNo +  "\",\n" +
                "  \"water_quality\": \"" + level + "\",\n" +
                "  \"river_speed\": \"" + river_speed + "\",\n" +
                "  \"river_flow\": \"" + flow + "\"\n" +
                "}}");
    }

    public static ArrayList<String> readFile(String path) throws IOException {
        File file = new File(path);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader br = new BufferedReader(isr);
        String line;
        ArrayList<String> als = new ArrayList<>();
        while ((line = br.readLine()) != null) als.add(line);
        return als;
    }

}
