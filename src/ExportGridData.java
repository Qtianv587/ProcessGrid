import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExportGridData {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.129.69:3306/jjsw";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String[] args) throws IOException {
        genGridData();
//        genGeoJsonFile();
//        intoDatabase();
    }

    public static void genGridData() throws IOException {
        String[] strings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
        classifyPoint(strings);
        //        String[] strings12 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "M", "N", "O"};
//        String[] strings01 = {"K", "L"};
        HashMap<String, String> hmssFlow = new HashMap<>();
        hmssFlow.put("A", "129.5");
        hmssFlow.put("B", "99.6");
        hmssFlow.put("C", "43.9");
        hmssFlow.put("D", "27.2");
        hmssFlow.put("E", "10");
        hmssFlow.put("F", "56.8");
        hmssFlow.put("G", "73.5");
        hmssFlow.put("H", "46");
        hmssFlow.put("I", "27.5");
        hmssFlow.put("J", "54.7");
        hmssFlow.put("K", "19.9");
        hmssFlow.put("L", "55.8");
        hmssFlow.put("M", "16.7");
        hmssFlow.put("N", "100.7");
        hmssFlow.put("O", "120.6");

        Integer[] diA = {50, 100, 0};
        Integer[] diB = {0, 20, 0};
        Integer[] diC = {0, 20, 0};
        Integer[] diD = {200, 200, 1};
        Integer[] diE = {0, 200, 0};
        Integer[] diF = {0, 200, 0};
        Integer[] diG = {200, 200, 1};
        Integer[] diH = {200, 200, 1};
        Integer[] diI = {200, 200, 1};
        Integer[] diJ = {200, 200, 1};
        Integer[] diK = {10, 100, 0};
        Integer[] diL = {0, 20, 0};
        Integer[] diM = {0, 100, 0};
        Integer[] diN = {200, 200, 1};
        Integer[] diO = {0, 200, 0};

        HashMap<String, Integer[]> hmsi = new HashMap<>();
        hmsi.put("A", diA);
        hmsi.put("B", diB);
        hmsi.put("C", diC);
        hmsi.put("D", diD);
        hmsi.put("E", diE);
        hmsi.put("F", diF);
        hmsi.put("G", diG);
        hmsi.put("H", diH);
        hmsi.put("I", diI);
        hmsi.put("J", diJ);
        hmsi.put("K", diK);
        hmsi.put("L", diL);
        hmsi.put("M", diM);
        hmsi.put("N", diN);
        hmsi.put("O", diO);


//        System.setOut(new PrintStream(new File("C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\PointAngle.txt")));
//        executeCompute(strings12, "12", hmssFlow, hmsi);
//        executeCompute(strings01, "01", hmssFlow, hmsi);
    }

    public static void executeCompute(String[] strings, String type, HashMap<String, String> hmss, HashMap<String, Integer[]> hmsi) throws IOException {
        for (String s: strings) {
            String path = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段" + s + "网格数据.txt";
            ArrayList<String> als = Test.readFile(path);
            ArrayList<String> alsCenter = new ArrayList<>();
            ArrayList<String> alsAngle = new ArrayList<>();
            ArrayList<String> alsLength = new ArrayList<>();
            ArrayList<String> alsGeoHash = new ArrayList<>();
            for (String line: als) {
                String[] locations = line.split("\\s+\\s+")[4].split(" ");
                double[] lngs = new double[4];
                double[] lats = new double[4];
                for (int i = 0; i < locations.length; i++) {
                    lngs[i] = Double.parseDouble(locations[i].split(",")[0]);
                    lats[i] = Double.parseDouble(locations[i].split(",")[1]);
                }
                String center = computeCenter(lngs, lats);
                alsCenter.add(center);
                alsGeoHash.add(GeoHash.getGeoHash(Double.parseDouble(center.split(",")[1]), Double.parseDouble(center.split(",")[0])));
                alsAngle.add(computeDirection(lngs, lats, type));
                alsLength.add(computeLength(lngs, lats, type));
            }

            //河段号+层次号+同层标号+是否末端+点1#点2#点3#点4+质心+角度+长度

            for (int i = 0; i < als.size(); i++) {
                String line = als.get(i);
                String[] coords = line.split("\\s+\\s+")[4].split(" ");
                System.out.print(coords[0] + "  " + coords[1] + "  " + coords[2] + "  " + coords[3] + "  " + coords[0] + "  ");
                System.out.println(alsCenter.get(i) + "  " + alsAngle.get(i) + "  " + hmss.get(s) + "  " + hmsi.get(s)[0] + "  " + hmsi.get(s)[1] + "  " + hmsi.get(s)[2]);
            }
        }
    }

    public static String computeCenter(double[] lngs, double[] lats) {
        double centerLng = (lngs[0] + lngs[1] + lngs[2] + lngs[3]) / 4;
        double centerLat = (lats[0] + lats[1] + lats[2] + lats[3]) / 4;
        return centerLng + "," + centerLat;
    }

    public static String computeDirection(double[] lngs, double[] lats, String type) {
        double[] v1 = new double[2];
        double[] v2 = new double[2];
        if (type.equals("01")) {
            v1[0] = lngs[1] - lngs[0];
            v1[1] = lats[1] - lats[0];
            v2[0] = lngs[2] - lngs[3];
            v2[1] = lats[2] - lats[3];
        } else if (type.equals("12")) {
            v1[0] = lngs[2] - lngs[1];
            v1[1] = lats[2] - lats[1];
            v2[0] = lngs[3] - lngs[0];
            v2[1] = lats[3] - lats[0];
        }
        double[] res = {v1[0] + v2[0], v1[1] + v2[1]};
        double resAngle = Math.toDegrees(Math.atan(res[1] / res[0]));
        if (res[0] < 0 && res[1] < 0) resAngle = 270 - resAngle;
        if (res[0] < 0 && res[1] > 0) resAngle = 270 - resAngle;
        if (res[0] > 0 && res[1] < 0) resAngle = 90 - resAngle;
        if (res[0] > 0 && res[1] > 0) resAngle = 90 - resAngle;
        return "" + resAngle;
    }

    public static String computeLength(double[] lngs, double[] lats, String type) {
        double length1 = 0;
        double length2 = 0;
        if (type.equals("01")) {
            length1 = getDistance(lats[1], lngs[1], lats[0], lngs[0]);
            length2 = getDistance(lats[2], lngs[2], lats[3], lngs[3]);
        } else if (type.equals("12")) {
            length1 = getDistance(lats[2], lngs[2], lats[1], lngs[1]);
            length2 = getDistance(lats[3], lngs[3], lats[0], lngs[0]);
        }
        double res = (length1 + length2) / 2;
        return "" + res;
    }

    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    //单位是米
    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
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

    //河段号+层次号+同层标号+是否末端+点1#点2#点3#点4+质心+角度+长度
    public static void intoDatabase() throws IOException {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
            ArrayList<String> als = Test.readFile("C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\allData2Database.txt");
            for (String line: als) {
                String[] fields = line.split("##");
                String  sql = "INSERT INTO `t2002_pol_spread_net`(`group`, `level`, small_level, `length`, is_last, coordinates, center, geohash, angle) \n" +
                        "VALUES('" + fields[0] + "', " + fields[1] + ", " + fields[2] +", " + fields[7] + ", " + fields[3] + ", '"+ fields[4] +"', '" + fields[5] + "', '" + fields[8] + "', "+ fields[6] +")";
                stmt.execute(sql);
            }
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public static void genGeoJsonFile() throws IOException {
        ArrayList<String> als = Test.readFile("C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\PointAngle.txt");
        ArrayList<String[]> alsPolygon = new ArrayList<>();
        ArrayList<String[]> alsPoint = new ArrayList<>();
        for (String line: als) {
            String[] fields = line.split("\\s+\\s+");
            String[] polygons = {fields[0], fields[1], fields[2], fields[3], fields[8], fields[9], fields[10]};
            String[] points = {fields[5], fields[6]};
            alsPolygon.add(polygons);
            alsPoint.add(points);
        }
        genGeoJsonPolygon(alsPolygon);
//        genGeoJsonPoint(alsPoint);
    }

    public static void genGeoJsonPolygon(ArrayList<String[]> als) throws FileNotFoundException {
        System.setOut(new PrintStream(new File("C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河网_堤防数据.json")));
        System.out.println("{\"type\":\"GeometryCollection\", \"geometries\": [");

        for (int i = 0; i < als.size(); i++) {
            String[] coord = als.get(i);
            System.out.print("{\"type\":\"Polygon\",\"coordinates\":[[");
            System.out.print("[" + coord[0] + "],[" + coord[1] + "],[" + coord[2] + "],[" + coord[3] + "],[" + coord[0] + "]]],");
            System.out.print("\"properties\": {\n" +
                    "        \"status_now\": \"" + coord[4] +"\",\n" +
                    "        \"standard\": \"" + coord[5] +"\",\n" +
                    "        \"is_achieved\": \"" + coord[6] +"\"\n" +

                    "}");
            if (i == als.size() - 1)
                System.out.print("}");
            else System.out.print("},");
        }
        System.out.println("]}");
    }

    public static void genGeoJsonPoint(ArrayList<String[]> als) throws FileNotFoundException {
        System.setOut(new PrintStream(new File("C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河网_方向数据.json")));
        System.out.println("{\"type\":\"GeometryCollection\", \"geometries\": [");

        for (int i = 0; i < als.size(); i++) {
            String[] coord = als.get(i);
            System.out.print("{\"type\":\"Point\",\"coordinates\":");
            System.out.print("[" + coord[0] + "],");
            System.out.print("\"properties\": {\n" +
                    "        \"angle\": \"" + coord[1] +"\"\n" + "}");
            if (i == als.size() - 1)
                System.out.print("}");
            else System.out.print("},");
        }
        System.out.println("]}");
    }

    public static void classifyPoint(String[] strings) throws IOException {
        HashMap<String, String> hmss = new HashMap<>();
        ArrayList<String> als = Test.readFile("C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\所有监测点.txt");
        for (String line: als) {
            double lng1 = Double.parseDouble(line.split(",")[0]);
            double lat1 = Double.parseDouble(line.split(",")[1]);
            double total = 99999;
            String result = "";
            for (int i = 0; i < strings.length; i++) {
                String path = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段" + strings[i] + "网格数据.txt";
                ArrayList<String> alsRiver = Test.readFile(path);
                ArrayList<Double[]> alsCenter = new ArrayList<>();
                for (String lineRiver: alsRiver) {
                    String[] locations = lineRiver.split("\\s+\\s+")[4].split(" ");
                    double[] lngs = new double[4];
                    double[] lats = new double[4];
                    for (int j = 0; j < locations.length; j++) {
                        lngs[j] = Double.parseDouble(locations[j].split(",")[0]);
                        lats[j] = Double.parseDouble(locations[j].split(",")[1]);
                    }
                    String center = computeCenter(lngs, lats);
                    Double[] tmp = {Double.parseDouble(center.split(",")[0]), Double.parseDouble(center.split(",")[1])};
                    alsCenter.add(tmp);
                }

                double flag = 99999;
                for (Double[] riverLoc: alsCenter) {
                    double len;
                    if ((len = getDistance(lat1, lng1, riverLoc[1], riverLoc[0])) < flag) {
                        flag = len;
                    }
                }
                if (flag < total) {
                    total = flag;
                    result = strings[i];
                }
            }
            hmss.put(line, result);
        }

        HashMap<String, String> klass = new HashMap<>();
        HashMap<String, String> klassRes = new HashMap<>();
        klass.put("城市", "AGHIJ");
        klass.put("工业", "BDFN");
        klass.put("农业", "CLMO");
        klass.put("自然", "EK");

        System.setOut(new PrintStream(new File("C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\PointClass.txt")));
        for (String key: hmss.keySet()) {
            String value = hmss.get(key);
            String belong = "";
            for (String key1: klass.keySet()) {
                if (klass.get(key1).contains(value)) {
                    belong = key1;
                    break;
                }
            }
            System.out.println(key + " " + belong);
            klassRes.put(key, belong);
        }

    }

}
