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
            addPath(LTHS_HALL_PATHS,LTHSHalls.EHALL.getHallway(),LTHSHalls.FHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.FHALL.getHallway(),LTHSHalls.LNHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.GHALL.getHallway(),LTHSHalls.LNHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.GHALL.getHallway(),LTHSHalls.HHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.LWHALL.getHallway(),LTHSHalls.FHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.LWHALL.getHallway(),LTHSHalls.LNHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.LWHALL.getHallway(),LTHSHalls.LSHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.LNHALL.getHallway(),LTHSHalls.LEHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.LEHALL.getHallway(),LTHSHalls.GHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.LEHALL.getHallway(),LTHSHalls.LSHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.HHALL.getHallway(),LTHSHalls.JHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.LEHALL.getHallway(),LTHSHalls.JHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.LSHALL.getHallway(),LTHSHalls.JHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.LSHALL.getHallway(),LTHSHalls.LHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.KHALL.getHallway(),LTHSHalls.LHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.JHALL.getHallway(),LTHSHalls.LHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.KHALL.getHallway(),LTHSHalls.HHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.KHALL.getHallway(),LTHSHalls.JHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.KHALL.getHallway(),LTHSHalls.M6.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.KHALL.getHallway(),LTHSHalls.ARTHALL.getHallway());
            addPath(LTHS_HALL_PATHS,LTHSHalls.ARTHALL.getHallway(),LTHSHalls.M6.getHallway());
            //Anex Paths
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
            EHALL("E Hall", E_HAll(), 20),
            FHALL("F Hall", F_HAll(),17),
            GHALL("G Hall", G_HAll(), 17),
            HHALL("H Hall", H_HAll(), 80),
            JHALL("J Hall", J_HAll(),17),
            LHALL("L Hall", L_HAll(),20 ),
            LEHALL("East Library Hall", new HashSet<>(), 30),
            LWHALL("West Library Hall", new HashSet<>(), 30),
            LNHALL("North Library Hall", new HashSet<>(), 10),
            ARTHALL("Art (Band/Choir) Hall", ART_HALL(),20),
            //Anex Halls
            M6("Link", M6_HAll(),100),
            M5("Annex Gym Hall", M5_HAll(),50),
            M4("Annex Science Hall", M5_HAll(),25),
            M3("Breezeway M3 Hall", M5_HAll(),80),
            M2("Annex History Hall", M2_HAll(),40),
            COURTYARD("Courtyard", COURTYARD(),50);
            private Hallway hallway;

            LTHSHalls(String name, HashSet<String> rooms, int distance){
                hallway = new Hallway(name, rooms, distance);
            }

            private static HashSet<String> B_HAll(){
                HashSet<String> B_ROOMS = new HashSet<>();
                B_ROOMS.add("B114");
                B_ROOMS.add("B113");
                B_ROOMS.add("B112");
                B_ROOMS.add("B111");
                B_ROOMS.add("B207");
                B_ROOMS.add("B206");
                B_ROOMS.add("B205");
                B_ROOMS.add("B203");
                B_ROOMS.add("B201");
                B_ROOMS.add("B200");
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
                D_ROOMS.add("D100");
                D_ROOMS.add("D120");
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
            private static HashSet<String> F_HAll(){
                HashSet<String> F_ROOMS = new HashSet<>();
                F_ROOMS.add("F101");
                F_ROOMS.add("F201");
                F_ROOMS.add("F202");
                F_ROOMS.add("F203");
                F_ROOMS.add("F204");
                F_ROOMS.add("F205");
                F_ROOMS.add("F206");
                F_ROOMS.add("F207");
                return F_ROOMS;
            }
            private static HashSet<String> G_HAll(){
                HashSet<String> G_ROOMS = new HashSet<>();
                G_ROOMS.add("G200");
                G_ROOMS.add("G201");
                G_ROOMS.add("G202");
                G_ROOMS.add("G203");
                G_ROOMS.add("G204");
                G_ROOMS.add("G205");
                G_ROOMS.add("G206");
                G_ROOMS.add("G207");
                return G_ROOMS;
            }
            private static HashSet<String> H_HAll(){
                HashSet<String> H_ROOMS = new HashSet<>();
                H_ROOMS.add("H200");
                H_ROOMS.add("H201");
                H_ROOMS.add("H202");
                H_ROOMS.add("H203");
                H_ROOMS.add("H205");
                H_ROOMS.add("H206");
                H_ROOMS.add("H210");
                H_ROOMS.add("H211");
                H_ROOMS.add("H212");
                H_ROOMS.add("H213");
                H_ROOMS.add("H214");
                H_ROOMS.add("H217");
                H_ROOMS.add("H218");
                H_ROOMS.add("H220");
                return H_ROOMS;
            }
            private static HashSet<String> J_HAll(){
                HashSet<String> J_ROOMS = new HashSet<>();
                J_ROOMS.add("J204");
                J_ROOMS.add("J205");
                J_ROOMS.add("J203");
                J_ROOMS.add("J206");
                J_ROOMS.add("J202");
                J_ROOMS.add("J207");
                J_ROOMS.add("J208");
                return J_ROOMS;
            }
            private static HashSet<String> L_HAll(){
            HashSet<String> L_ROOMS = new HashSet<>();
                L_ROOMS.add("L103");
                L_ROOMS.add("L104");
                L_ROOMS.add("L107");
                return L_ROOMS;
            }

            private static HashSet<String> ART_HALL(){
                HashSet<String> ART_ROOMS = new HashSet<>();
                ART_ROOMS.add("L141");
                ART_ROOMS.add("L140");
                ART_ROOMS.add("L129");
                ART_ROOMS.add("L136");
                ART_ROOMS.add("L135");
                ART_ROOMS.add("L134");
                ART_ROOMS.add("L133");
                ART_ROOMS.add("L130");
                return ART_ROOMS;
            }
            private static HashSet<String> M6_HAll(){
                HashSet<String> M6_ROOMS = new HashSet<>();
                M6_ROOMS.add("M604");
                M6_ROOMS.add("M602");
                return M6_ROOMS;
            }

            //Anex hallways

            private static HashSet<String> M5_HAll(){
                HashSet<String> M5_ROOMS = new HashSet<>();
                M5_ROOMS.add("M511");
                M5_ROOMS.add("M505");
                M5_ROOMS.add("M501");
                return M5_ROOMS;
            }

            private static HashSet<String> M3_HAll(){
                HashSet<String> M3_ROOMS = new HashSet<>();
                M3_ROOMS.add("M307");
                M3_ROOMS.add("M310");
                M3_ROOMS.add("M311");
                M3_ROOMS.add("M312");
                M3_ROOMS.add("M316");
                M3_ROOMS.add("M318");
                M3_ROOMS.add("M319");
                M3_ROOMS.add("M320");
                M3_ROOMS.add("M321");
                M3_ROOMS.add("M325");
                return M3_ROOMS;
            }

            private static HashSet<String> M4_HAll(){
                HashSet<String> M4_ROOMS = new HashSet<>();
                M4_ROOMS.add("M407");
                M4_ROOMS.add("M403");
                M4_ROOMS.add("M404");
                M4_ROOMS.add("M401");
                M4_ROOMS.add("M402");
                return M4_ROOMS;
            }
            private static HashSet<String> M2_HAll(){
                HashSet<String> M2_ROOMS = new HashSet<>();
                M2_ROOMS.add("M218");
                M2_ROOMS.add("M219");
                M2_ROOMS.add("M217");
                M2_ROOMS.add("M215");
                M2_ROOMS.add("M212");
                M2_ROOMS.add("M211");
                M2_ROOMS.add("M210");
                M2_ROOMS.add("M220");
                M2_ROOMS.add("M209");
                M2_ROOMS.add("M208");
                M2_ROOMS.add("M206");
                M2_ROOMS.add("M207");
                M2_ROOMS.add("M205");
                M2_ROOMS.add("M202");
                M2_ROOMS.add("M201");
                M2_ROOMS.add("M221");
                return M2_ROOMS;
            }

            private static HashSet<String> COURTYARD(){
                HashSet<String> COURTYARD_ROOMS = new HashSet<>();
                COURTYARD_ROOMS.add("M307");
                COURTYARD_ROOMS.add("M310");
                COURTYARD_ROOMS.add("M311");
                COURTYARD_ROOMS.add("M312");
                COURTYARD_ROOMS.add("M316");
                COURTYARD_ROOMS.add("M318");
                COURTYARD_ROOMS.add("M319");
                COURTYARD_ROOMS.add("M320");
                COURTYARD_ROOMS.add("M321");
                COURTYARD_ROOMS.add("M325");
                COURTYARD_ROOMS.add("M101");
                COURTYARD_ROOMS.add("M102");
                COURTYARD_ROOMS.add("M103");
                COURTYARD_ROOMS.add("M104");
                COURTYARD_ROOMS.add("M105");
                COURTYARD_ROOMS.add("M106");
                COURTYARD_ROOMS.add("M107");
                COURTYARD_ROOMS.add("M108");
                COURTYARD_ROOMS.add("M109");
                COURTYARD_ROOMS.add("M110");
                COURTYARD_ROOMS.add("M111");
                COURTYARD_ROOMS.add("M112");
                COURTYARD_ROOMS.add("M113");
                COURTYARD_ROOMS.add("M114");
                COURTYARD_ROOMS.add("M115");
                COURTYARD_ROOMS.add("M116");
                COURTYARD_ROOMS.add("M117");
                COURTYARD_ROOMS.add("M1118");
                COURTYARD_ROOMS.add("M119");
                COURTYARD_ROOMS.add("M120");
                COURTYARD_ROOMS.add("M121");
                COURTYARD_ROOMS.add("M122");
                COURTYARD_ROOMS.add("M123");
                COURTYARD_ROOMS.add("M124");
                return COURTYARD_ROOMS;
            }

            Hallway getHallway(){
                return this.hallway;
            }

        }
    }

