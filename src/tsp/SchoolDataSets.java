package tsp;

import java.util.HashMap;
import java.util.HashSet;

public enum SchoolDataSets {

        LTHS(new SchoolData(LTHSMap()));
        private SchoolData schoolDataMap;
        SchoolDataSets(SchoolData schoolDataMap){
            this.schoolDataMap = schoolDataMap;
        }

        private static HashMap<Path,Integer> LTHSMap(){
            HashMap<Path,Integer> LTHSMap = new HashMap<>();
            /*addPath(LTHSMap, "B","C",25);
            addPath(LTHSMap, "B","D",35);
            addPath(LTHSMap, "B","E",20);
            addPath(LTHSMap, "B","K",65);
            addPath(LTHSMap, "C","D",30);
            addPath(LTHSMap, "C","E",35);
            addPath(LTHSMap, "C","K",95);
            addPath(LTHSMap, "D","E",30);
            addPath(LTHSMap, "D","K",95);
            addPath(LTHSMap, "E","K",90);*/
            //addPath(LTHSMap,new Hallway("B hall"));
            return LTHSMap;

        }
        private static <E> void addPath(HashMap map, E point1, E point2, Integer Distance){
            map.put(new Path(point1, point2),Distance);
        }
        SchoolData getSchoolData(){
            return this.schoolDataMap;
        }
    }

