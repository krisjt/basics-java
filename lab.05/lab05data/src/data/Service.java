package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Service {
    public static List<Painter> paintersList = new ArrayList<>(){
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < paintersList.size(); i++){
            string.append(paintersList.get(i));
        }
        return string.toString();
    }
};

    static List<String> list = new ArrayList<>(){
        @Override
        public String toString(){
            StringBuilder string2 = new StringBuilder();
            for(String o: list){

                string2.append(o).append(" ");
            }
            return string2.toString();
        }
    };
    public static final List<List<String>> fence = new ArrayList<>(){
        @Override
        public String toString(){
            StringBuilder string = new StringBuilder();
            for(List<String> o: fence){
                string.append(o).append(" ");
            }
            return string.toString();
        }
    };

    public static final List<Boolean> segmentTaken = new ArrayList<>();
    public static List<List<String>> isPlaceTaken = new ArrayList<>();
    static List<String> innerTakenList = new ArrayList<>();

    public static void fenceInitialization(Supplier supplier){
        Random rand = new Random();
        int segNum = rand.nextInt(20)+5;

        for(int i = 0; i < segNum; i++){
            list = new ArrayList<>();
            innerTakenList = new ArrayList<>();
            for(int j = 0; j < 10; j++) {
                list.add("-");
                innerTakenList.add("-");
            }
            fence.add(list);
            segmentTaken.add(false);
            isPlaceTaken.add(innerTakenList);
        }
        System.out.println(isPlaceTaken);
        paintersCreation(supplier,segNum);
    }

    public static void paintersCreation(Supplier supplier, int minCount){
        Random rand2 = new Random();
        int paintNum = 100;
        while(paintNum > minCount) paintNum = rand2.nextInt(24)+1;

        for(int i = 0; i < paintNum; i++) {
            int a = i+65;
            String name = String.valueOf((char)a);

            Painter painter = new Painter(name, rand2.nextInt(5000)+200, 10, supplier);
            paintersList.add(painter);
            painter.start();

        }

    }
    public static void waitForPaintersToFinish(List<Painter> painters) {
        for (Painter painter : painters) {
            try {
                painter.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

