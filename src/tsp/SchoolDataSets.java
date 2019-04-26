package tsp;

import java.util.HashSet;

public enum SchoolDataSets {

        LTHS(new Solver(LTHSdata(),LTHSHallsHashSet()));

        private Solver schoolData;
        SchoolDataSets(Solver schoolData){
            this.schoolData = schoolData;
        }

        private static HashSet<Path> LTHSdata(){
            HashSet<Path> LTHS_HALL_PATHS = new HashSet<>();
            addPath(LTHS_HALL_PATHS,LTHSHalls.BHALL.getHallway(),LTHSHalls.CHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.BHALL.getHallway(),LTHSHalls.LSHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.CHALL.getHallway(),LTHSHalls.LSHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.LSHALL.getHallway(),LTHSHalls.KHALL.getHallway());
            //Solver solver = new Solver(LTHS_HALL_PATHS);
            //generate map
            return LTHS_HALL_PATHS;
        }


        //private static void addHall
        private static <E> void addPath(HashSet<Path> paths,E hall1,E hall2){
            paths.add(new Path(hall1,hall2));
        }

        Solver getSchoolData(){
            return this.schoolData;
        }
        private static HashSet<Hallway> LTHSHallsHashSet(){
            HashSet<Hallway> halls = new HashSet<>();
            for(LTHSHalls LTHShall:LTHSHalls.values()){
                halls.add(LTHShall.getHallway());
            }
            return halls;
        }
        private enum LTHSHalls{

            BHALL("B hall", B_HAll(),20),
            CHALL("C hall", C_HAll(), 20),
            LSHALL("Main hall",new HashSet<>(),50),
            KHALL("K Hall", K_HAll(), 20);

            private Hallway hallway;

            LTHSHalls(String name, HashSet<String> rooms, int distance){
                this.hallway = new Hallway(name, rooms, distance);
            }

            static HashSet<String> B_HAll(){
                HashSet<String> B_ROOMS = new HashSet<>();
                B_ROOMS.add("B114");
                B_ROOMS.add("B113");
                B_ROOMS.add("B112");
                B_ROOMS.add("B207");
                B_ROOMS.add("B205");
                B_ROOMS.add("B203");
                return B_ROOMS;
            }

            static HashSet<String> C_HAll(){
                HashSet<String> C_ROOMS = new HashSet<>();
                C_ROOMS.add("C200");
                C_ROOMS.add("C201");
                C_ROOMS.add("C202");
                C_ROOMS.add("C206");
                C_ROOMS.add("C207");
                C_ROOMS.add("C208");
                C_ROOMS.add("C209");
                C_ROOMS.add("C210");
                C_ROOMS.add("C211");
                C_ROOMS.add("C103");
                C_ROOMS.add("C105");
                C_ROOMS.add("C106");
                C_ROOMS.add("C109");
                C_ROOMS.add("C108");
                C_ROOMS.add("C111");
                C_ROOMS.add("C110");
                C_ROOMS.add("C113");
                C_ROOMS.add("C112");
                return C_ROOMS;
            }

            static HashSet<String> K_HAll(){
                HashSet<String> K_ROOMS = new HashSet<>();
                K_ROOMS.add("K100");
                K_ROOMS.add("K101");
                K_ROOMS.add("K102");
                K_ROOMS.add("K103");
                K_ROOMS.add("K104");
                K_ROOMS.add("K105");
                K_ROOMS.add("K106");
                K_ROOMS.add("K107");
                K_ROOMS.add("K109");
                K_ROOMS.add("K203");
                K_ROOMS.add("K204");
                K_ROOMS.add("K205");
                K_ROOMS.add("K207");
                K_ROOMS.add("K208");
                K_ROOMS.add("K209");
                K_ROOMS.add("K210");
                K_ROOMS.add("K211");
                K_ROOMS.add("K215");
                K_ROOMS.add("K214");
                return K_ROOMS;
            }

            Hallway getHallway(){
                return this.hallway;
            }
        }

    }

