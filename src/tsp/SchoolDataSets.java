package tsp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.EnumSet;

public enum SchoolDataSets {

        LTHS(new SchoolData(LTHSdata(),new HashSet(EnumSet.allOf(LTHSHalls.class))));

        private SchoolData schoolData;
        enum LTHSHalls{

            BHALL("B hall", null,20),
            CHALL("C hall", null, 20),
            LSHALL("Main hall",null,50),
            KHALL("K Hall", null, 20);


            private Hallway hallway;

            LTHSHalls(String name, HashSet<String> rooms, int distance){
                this.hallway = new Hallway(name, rooms, distance);
            }

            Hallway getHallway(){
                return this.hallway;
            }
        }

        SchoolDataSets(SchoolData schoolData){
            this.schoolData = schoolData;
        }


        private static HashSet<Path> LTHSdata(){
            HashSet<Path> LTHSdata = new HashSet<>();
            addPath(LTHSdata,LTHSHalls.BHALL.getHallway(),LTHSHalls.CHALL.getHallway());
            addPath(LTHSdata,LTHSHalls.BHALL.getHallway(),LTHSHalls.LSHALL.getHallway());
            addPath(LTHSdata,LTHSHalls.CHALL.getHallway(),LTHSHalls.LSHALL.getHallway());
            addPath(LTHSdata,LTHSHalls.LSHALL.getHallway(),LTHSHalls.KHALL.getHallway());

            return LTHSdata;

        }
        private static <E> void addPath(HashMap map, E point1, E point2, Integer Distance){
            map.put(new Path(point1, point2),Distance);
        }
        //private static void addHall
        private static <E> void addPath(HashSet<Path> paths,E hall1,E hall2){
            paths.add(new Path(hall1,hall2));
        }
        SchoolData getSchoolData(){
            return this.schoolData;
        }
    }

