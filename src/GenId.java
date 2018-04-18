import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GenId {
    public static void main(String[] args) throws FileNotFoundException {
        //走马河path
        String pathZouma = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\走马河.kml";
        //主河道path
        String pathZhuhe = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\锦江主河道.kml";
        //A
        String[] riverAStart = {"103.617786084,30.9981685166", "103.61776152,30.9980912889", "103.617934038,30.9980488457", "103.617965826,30.9981257254"};
        String[] riverAEnd = {"103.619199096,30.9973692266","103.619110531,30.9972654715","103.619293505,30.9970851429","103.619419906,30.9971967045"};
        //B
        String[] riverBStart = {"103.619419906,30.9971967045","103.619293505,30.9970851429","103.619340143,30.9970507802","103.619468653,30.997134401"};
        String[] riverBEnd = {"103.680873975,30.9442549069","103.68082003,30.9441058151","103.682394215,30.9432233127","103.682502488,30.9433778139"};
        //C
        String[] riverCStart = {"103.682502488,30.9433778139","103.682394215,30.9432233127","103.682580257,30.9429265269","103.682622967,30.9430357392"};
        String[] riverCEnd = {"103.800861073,30.8489420328","103.800813423,30.8488833555","103.801250985,30.8484077946","103.801362903,30.8484489048"};
        //D
        String[] riverDStart = {"103.801362903,30.8484489048","103.801250985,30.8484077946","103.801504723,30.8473661286","103.801607217,30.8473749881"};
        String[] riverDEnd = {};//走马河终点
        //E
        String[] riverEStart = {"103.6207770217203,30.99726281567943","103.6208445852642,30.9972718682359","103.6219355299521,30.99648087765664","103.6219116440122,30.99643133256521"};
        String[] riverEEnd = {"103.9638053984869,30.84058098379391","103.9639358864934,30.84054017004048","103.963499442552,30.8399343891502","103.9632762856541,30.83996985769164"};
        //F
        String[] riverFStart = {"103.9632762856541,30.83996985769164","103.963499442552,30.8399343891502","103.9643760104728,30.83858762099985","103.9641018939927,30.83856795125001"};
        String[] riverFEnd = {"104.0135256436816,30.74965791310381","104.0135651705087,30.74978985765394","104.0152982889407,30.74941323166114","104.0153187043262,30.74928162028877"};
        //G
        String[] riverGStart = {"104.0153187043262,30.74928162028877","104.0152982889407,30.74941323166114","104.0164026625104,30.75017241325973","104.0164159970126,30.750049470987"};
        String[] riverGEnd = {"104.0466971822666,30.72111270511324","104.0468762440268,30.7212137706566","104.0470996456029,30.72042195936692","104.0469031377937,30.72033235933826"};
        //沙河 H
        String[] riverHStart = {"104.047497089,30.7206336594", "104.04746402,30.7207421136", "104.047802247,30.7206923243", "104.047845966,30.7206236505"};
        String[] riverHEnd = {};
        //I
        String[] riverIStart = {"104.0469031377937,30.72033235933826","104.0470996456029,30.72042195936692","104.0474162686645,30.71987024458628","104.0473199887945,30.71980330578445"};
        String[] riverIEnd = {"104.0813227777098,30.64712480576439","104.0815208818326,30.64732829418788","104.0810086937919,30.64503463772299","104.0806496400681,30.64496664133059"};
        //J
        String[] riverJStart = {"104.0806496400681,30.64496664133059","104.0810086937919,30.64503463772299","104.082611972475,30.6443210672977","104.0822998710026,30.64422278799429"};
        String[] riverJEnd = {"104.0850280209957,30.61121219983446","104.0853542793497,30.61129389438519","104.0854779014314,30.61000762310544","104.0851595377075,30.60998611652987"};
        //江安河 K
        String[] riverKStart = {"103.619010074,30.9969242559", "103.619004912,30.9964195099", "103.619067281,30.9964579909", "103.619075059,30.996910211"};
        //徐堰河 L
        String[] riverLStart = {"103.682539989,30.9431702066", "103.68288195,30.9429889929", "103.682904895,30.9430229839", "103.682565487,30.9432054506"};
        //沱江河 M
        String[] riverMStart = {"103.801430782,30.8482817728", "103.801449965,30.8482060077", "103.801660288,30.8480160645", "103.801667391,30.8480674817"};
        //N
        String[] riverNStart = {"104.0851595377075,30.60998611652987","104.0854779014314,30.61000762310544","104.0854020889601,30.60833813655027","104.0850212950154,30.60843298007128"};
        String[] riverNEnd = {"104.0526724769797,30.49895688950597","104.0530393587299,30.49903130183041","104.0537639277535,30.49752087210364","104.0533468404298,30.49735802123062"};
        //O
        String[] riverOStart = {"104.0533468404298,30.49735802123062","104.0537639277535,30.49752087210364","104.0545879276994,30.49590474964521","104.054204267718,30.49567146034267"};
        String[] riverOEnd = {};
        ArrayList<String[]> alsAllZouma = readKml(pathZouma, riverAStart);
        ArrayList<String[]> alsAllZhuhe = readKml(pathZhuhe, riverEStart);
        String pathOutA = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段A网格数据.txt";
        String pathOutB = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段B网格数据.txt";
        String pathOutC = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段C网格数据.txt";
        String pathOutD = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段D网格数据.txt";
        String pathOutE = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段E网格数据.txt";
        String pathOutF = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段F网格数据.txt";
        String pathOutG = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段G网格数据.txt";
        String pathOutI = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段I网格数据.txt";
        String pathOutJ = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段J网格数据.txt";
        String pathOutN = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段N网格数据.txt";
        String pathOutO = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\河段O网格数据.txt";
        genLayerId1(alsAllZouma, pathOutA, pathZouma, riverAStart, riverAEnd);
        genLayerId1(alsAllZouma, pathOutB, pathZouma, riverBStart, riverBEnd);
        genLayerId1(alsAllZouma, pathOutC, pathZouma, riverCStart, riverCEnd);
        genLayerId1(alsAllZouma, pathOutD, pathZouma, riverDStart);
        genLayerId1(alsAllZhuhe, pathOutE, pathZhuhe, riverEStart, riverEEnd);
        genLayerId1(alsAllZhuhe, pathOutF, pathZhuhe, riverFStart, riverFEnd);
        genLayerId1(alsAllZhuhe, pathOutG, pathZhuhe, riverGStart, riverGEnd);
        genLayerId1(alsAllZhuhe, pathOutI, pathZhuhe, riverIStart, riverIEnd);
        genLayerId1(alsAllZhuhe, pathOutJ, pathZhuhe, riverJStart, riverJEnd);
        genLayerId1(alsAllZhuhe, pathOutN, pathZhuhe, riverNStart, riverNEnd);
        genLayerId1(alsAllZhuhe, pathOutO, pathZhuhe, riverOStart);
    }

    //江安河 徐堰河
    public static void genLayerId(String path, String[] root) throws FileNotFoundException {
        ArrayList<String[]> alsa = readKml(path, root);
        HashMap<Integer, ArrayList<String[]>> res = new HashMap<>();
        String[] tmp;

        ArrayList<String[]> als0 = new ArrayList<>();
        als0.add(root);
        res.put(0, als0);

        int i = 1;
        //按层次递增添加网格，层次号就是map的key
        while ((tmp = getNextLayer(alsa, root)) != null) {
            ArrayList<String[]> als = new ArrayList<>();
            als.add(tmp);
//            System.out.println(tmp[0] + "-" + tmp[1] + "-" + tmp[2] + "-" + tmp[3]);
            res.put(i, als);
            root = tmp;
            i ++;
        }
//        System.out.println(res.size());
        //添加同一层次的网格，层次号就是map的key
        for (int j = 0; j < res.size(); j++) {
            String[] strings = res.get(j).get(0);
            while ((tmp = getSameLayer(alsa, strings)) != null) {
                res.get(j).add(tmp);
                strings = tmp;
            }
        }

        String pathOut = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\江安河网格数据.txt";
        printOut(pathOut, res);
    }

    //沙河  沱江河
    public static void genLayerId1(ArrayList<String[]> alsAll, String pathOut, String path, String[] root) throws FileNotFoundException {
        ArrayList<String[]> alsa = readKml(path, root);
        HashMap<Integer, ArrayList<String[]>> res = new HashMap<>();
        String[] tmp;
        ArrayList<String[]> als0 = new ArrayList<>();
        als0.add(root);
        res.put(0, als0);

        int i = 1;
        //按层次递增添加网格，层次号就是map的key
        while ((tmp = getSameLayer(alsa, root)) != null) {
            ArrayList<String[]> als = new ArrayList<>();
            als.add(tmp);
//            System.out.println(tmp[0] + "-" + tmp[1] + "-" + tmp[2] + "-" + tmp[3]);
            res.put(i, als);
            root = tmp;
            i ++;
        }
//        System.out.println(res.size());
        //添加同一层次的网格，层次号就是map的key
        for (int j = 0; j < res.size(); j++) {
            String[] strings = res.get(j).get(0);
            while ((tmp = getNextLayer(alsAll, strings)) != null) {
                res.get(j).add(tmp);
                strings = tmp;
            }
        }
//        String pathOut = "C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\沱江河网格数据.txt";
        printOut(pathOut, res);
    }

    //走马河 主河道
    public static void genLayerId1(ArrayList<String[]> alsaALL, String pathOut, String path, String[] start, String[] end) throws FileNotFoundException {
        ArrayList<String[]> alsa = readKml(path, start, end);
        HashMap<Integer, ArrayList<String[]>> res = new HashMap<>();
        String[] tmp;
        ArrayList<String[]> als0 = new ArrayList<>();
        als0.add(start);
        res.put(0, als0);

        int i = 1;
        //按层次递增添加网格，层次号就是map的key
        while ((tmp = getSameLayer(alsa, start)) != null) {
            ArrayList<String[]> als = new ArrayList<>();
            als.add(tmp);
//            System.out.println(tmp[0] + "-" + tmp[1] + "-" + tmp[2] + "-" + tmp[3]);
            res.put(i, als);
            start = tmp;
            i ++;
        }
//        System.out.println(res.size());
        //添加同一层次的网格，层次号就是map的key
        for (int j = 0; j < res.size(); j++) {
            String[] strings = res.get(j).get(0);
            while ((tmp = getNextLayer(alsaALL, strings)) != null) {
                res.get(j).add(tmp);
                strings = tmp;
            }
        }

//        for (int j = 0; j < res.size(); j++) {
//            for (String[] strings: res.get(j)) {
//                System.out.println("[" + strings[0] + "],[" + strings[1] + "],[" + strings[2] + "],[" + strings[3] + "],");
//            }
//        }
        printOut(pathOut, res);

    }

    public static ArrayList<String[]> getNodes(Element node) {
        List<Element> listElement=node.elements("Placemark");//
        ArrayList<String[]> alsa = new ArrayList<>();
        for(Element e:listElement){//
            Element element1 = e.element("Polygon");
            Element element2 = element1.element("outerBoundaryIs");
            Element element3 = element2.element("LinearRing");
            Element element4 = element3.element("coordinates");
            String[] strings = element4.getText().replaceAll("[\\s*]", "").split(",0");
            alsa.add(strings);
        }

        return alsa;
    }
    public static ArrayList<String[]> getNodes(Element node, String[] root) {
        List<Element> listElement=node.elements("Placemark");//
        ArrayList<String[]> alsa = new ArrayList<>();
        int flag = 0;
        for(Element e:listElement){//
            Element element1 = e.element("Polygon");
            Element element2 = element1.element("outerBoundaryIs");
            Element element3 = element2.element("LinearRing");
            Element element4 = element3.element("coordinates");
            String[] strings = element4.getText().replaceAll("[\\s*]", "").split(",0");
            if (Arrays.equals(strings, root)) flag = 1;
            if (flag == 1) alsa.add(strings);
        }

        return alsa;
    }
    public static ArrayList<String[]> getNodes(Element node, String[] start, String[] end) {
        List<Element> listElement=node.elements("Placemark");//
        ArrayList<String[]> alsa = new ArrayList<>();
        int flag = 0;
        int k = 0;
        for(Element e:listElement){
            Element element1 = e.element("Polygon");
            Element element2 = element1.element("outerBoundaryIs");
            Element element3 = element2.element("LinearRing");
            Element element4 = element3.element("coordinates");
            String[] strings = element4.getText().replaceAll("[\\s*]", "").split(",0");
            for (int i = 0; i < start.length; i++) {
                start[i] = start[i].replaceAll("[\\s*]", "");
            }
            if (Arrays.equals(strings, start)) {
                flag = 1;
            } else if (Arrays.equals(strings, end)) {
                alsa.add(strings);
                flag = 0;
            }
            if (flag == 1) alsa.add(strings);
        }

        return alsa;
    }


    public static String[] getNextLayer(ArrayList<String[]> alsa, String[] root) {
        for (String[] sa: alsa) {
            if (root[1].equals(sa[0]) && root[2].equals(sa[3])) {
                return sa;
            }
        }
        return null;
    }

    public static String[] getSameLayer(ArrayList<String[]> alsa, String[] root) {
        for (String[] sa: alsa) {
            if (root[2].equals(sa[1]) && root[3].equals(sa[0])) {
                return sa;
            }
        }
        return null;
    }

    public static ArrayList<String[]> readKml(String path, String[] start, String[] end) {
        SAXReader reader = new SAXReader();
        ArrayList<String[]> alsa = new ArrayList<>();
        try {
            Document document = reader.read(new File(path));
            Element head=document.getRootElement();
            Element folder = head.element("Document");
            alsa = getNodes(folder, start, end);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return alsa;
    };
    public static ArrayList<String[]> readKml(String path, String[] root) {
        SAXReader reader = new SAXReader();
        ArrayList<String[]> alsa = new ArrayList<>();
        try {
//            Document document = reader.read(new File("C:\\Users\\Administrator\\IdeaProjects\\ProcessGrid\\resources\\徐堰河.kml"));
            Document document = reader.read(new File(path));
            Element head=document.getRootElement();
            Element folder = head.element("Document");
            alsa = getNodes(folder, root);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return alsa;
    };

    public static void printOut(String path, HashMap<Integer, ArrayList<String[]>> res) throws FileNotFoundException {
        System.setOut(new PrintStream(new File(path)));
        for (int j = 0; j < res.size(); j++) {
            ArrayList<String[]> als = res.get(j);
            for (int k = 0; k < als.size(); k ++) {
                String[] strings = als.get(k);
                System.out.print("RIVER_NO  ");
                System.out.print(j + "  ");
                System.out.print(k + "  ");
                if (j == res.size() - 1) {
                    System.out.print("1  ");
                } else {
                    System.out.print("0  ");
                }
                System.out.println(strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3]);
            }
        }
    }
}
