package tsp;

import java.util.HashSet;
import java.util.List;

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
            addPath(LTHS_HALL_PATHS,LTHSHalls.BHALL.getHallway(),LTHSHalls.DHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.BHALL.getHallway(),LTHSHalls.EHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.CHALL.getHallway(),LTHSHalls.LSHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.CHALL.getHallway(),LTHSHalls.EHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.CHALL.getHallway(),LTHSHalls.DHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.DHALL.getHallway(),LTHSHalls.EHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.DHALL.getHallway(),LTHSHalls.LSHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.EHALL.getHallway(),LTHSHalls.LSHALL.getHallway());
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

        List<String> bestPath(HashSet<String> schedule) throws NonHamiltonianTourPointsException{
            return this.schoolData.bestPathStartingAnywhere(schedule);
        }

    private enum LTHSHalls{

            BHALL("B hall", B_HAll(),20),
            CHALL("C hall", C_HAll(), 20),
            LSHALL("Main hall",new HashSet<>(),50),
            KHALL("K Hall", K_HAll(), 20),
            DHALL("D Hall", D_HAll(), 30),
            EHALL("E Hall", E_HAll(), 20);
            private Hallway hallway;

            LTHSHalls(String name, HashSet<String> rooms, int distance){
                this.hallway = new Hallway(name, rooms, distance);
            }

            private static HashSet<String> B_HAll(){
                HashSet<String> B_ROOMS = new HashSet<>();
                B_ROOMS.add("B114");
                B_ROOMS.add("B113");
                B_ROOMS.add("B112");
                B_ROOMS.add("B207");
                B_ROOMS.add("B205");
                B_ROOMS.add("B203");
                return B_ROOMS;
            }

            private static HashSet<String> C_HAll(){
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

            private static HashSet<String> K_HAll(){
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
            private static HashSet<String> E_HAll(){
                HashSet<String> E_ROOMS = new HashSet<>();
                E_ROOMS.add("E102");
                E_ROOMS.add("E103");
                E_ROOMS.add("E104");
                E_ROOMS.add("E204");
                E_ROOMS.add("E205");
                E_ROOMS.add("E206");
                E_ROOMS.add("E207");
                return E_ROOMS;
            }
            private static HashSet<String> D_HAll(){
                HashSet<String> D_ROOMS = new HashSet<>();
                D_ROOMS.add("D102");
                D_ROOMS.add("D103");
                D_ROOMS.add("D104");
                D_ROOMS.add("D105");
                D_ROOMS.add("D202");
                D_ROOMS.add("D203");
                D_ROOMS.add("D204");
                D_ROOMS.add("D205");
                D_ROOMS.add("D206");
                return D_ROOMS;
            }
            Hallway getHallway(){
                return this.hallway;
            }
        }

    }

