/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.utils;

/**
 *
 * @author user1
 */
public class Path {

    public static abstract class Flow {

        private String path;

        public Flow(String path) {
            this.path = path;
        }

        public Flow(Flow flow, String path) {
            this.path = flow.path + path;
        }

        @Override
        public String toString() {
            return path;
        }
    }

    private static class Resources extends Flow {

        private Resources() {
            super("/resources");
        }
    }

    public static class Imgs extends Flow {

        private Imgs() {
            super(new Resources(), "/imgs");
        }

        public static class Actors extends Flow {

            private Actors(Flow flow) {
                super(flow, "/actors");
            }

            public String aircraft() {
                return this + "/airplane1.png";
            }

            public String enemy() {
                return this + "/enemy1.png";
            }
            
            public String actor() {
                return this + "/Actor1.png";
            }
            public String testImg() {
                return this + "/testimg.png";
            }
            public String master() {
                return this + "/master.png";
            }
            public String master1() {
                return this + "/master1.png";
            }
            public String master2() {
                return this + "/master2.png";
            }
            public String panda1() {
                return this + "/Pa1.png";
            }
            public String panda2() {
                return this + "/Pa2.png";
            }
            public String panda3() {
                return this + "/Pa3.png";
            }
            public String panda4() {
                return this + "/Pa4.png";
            }
            public String monster() {
                return this + "/Monster.png";
            }
            public String monster1() {
                return this + "/Monster1.png";
            }
            public String rip(){
                return this + "/RIP.png";
            }
            
        }

        public static class Objs extends Flow {

            private Objs(Flow flow) {
                super(flow, "/objs");
            }

            public String boom() {
                return this + "/boom.png";
            }

            public String boom2() {
                return this + "/boom2.png";
            }
            public String arrowUp(){
                return this + "/ArrowUp.png";
            }
            public String arrowDown() {
                return this + "/ArrowDown.png";
            }
            public String arrowLeft() {
                return this + "/ArrowLeft.png";
            }
            public String arrowRight() {
                return this + "/ArrowRight.png";
            }
            public String greenArrowUp(){
                return this + "/GreenArrowUp.png";
            }
            public String greenArrowDown() {
                return this + "/GreenArrowDown.png";
            }
            public String greenArrowLeft() {
                return this + "/GreenArrowLeft.png";
            }
            public String greenArrowRight() {
                return this + "/GreenArrowRight.png";
            }
            public String perfect(){
                return this + "/Perfect.png";
            }
            public String good(){
                return this + "/Good.jpg";
            }
            public String miss(){
                return this + "/Miss.png";
            }
            public String blackArrowUp(){
                return this + "/BlackArrowUp.png";
            }
            public String blackArrowDown() {
                return this + "/BlackArrowDown.png";
            }
            public String blackArrowLeft() {
                return this + "/BlackArrowLeft.png";
            }
            public String blackArrowRight() {
                return this + "/BlackArrowRight.png";
            }
            public String number(){
                return this +"/number10.jpg";
            }
            public String showscore(){
                return this +"/score1.png";
            }
            public String showNumber0(){
                return this +"/0.png";
            }
            
            public String showNumber1(){
                return this +"/1.png";
            }
            public String showNumber2(){
                return this +"/2.png";
            }
            public String showNumber3(){
                return this +"/3.png";
            }
            public String showNumber4(){
                return this +"/4.png";
            }
            public String showNumber5(){
                return this +"/5.png";
            }
            public String showNumber6(){
                return this +"/6.png";
            }
            public String showNumber7(){
                return this +"/7.png";
            }
            public String showNumber8(){
                return this +"/8.png";
            }
            public String showNumber9(){
                return this +"/9.png";
            }
             public String colon(){
                return this +"/colon.png";
            }
             public String maxCombo(){
                 return this + "/maxcombo.png";
             }
             public String showBlueScore(){
                return this +"/score2.png";
            }
            public String showplay1() {
                return this + "/play1.png";
            }
            public String showplay2() {
                return this + "/play2.png";
            }
            public String pandaSmall(){
                return this + "/pandaSmall.png";
            }
            public String monsterHpBlack(){
                return this + "/hpBlack.png";
            }
            public String monsterHpFull(){
                return this + "/hpFull.png";
            }
            public String goldNumber0(){
                return this +"/goldNumber0.png";
            }
            
            public String goldNumber1(){
                return this +"/goldNumber1.png";
            }
            public String goldNumber2(){
                return this +"/goldNumber2.png";
            }
            public String goldNumber3(){
                return this +"/goldNumber3.png";
            }
            public String goldNumber4(){
                return this +"/goldNumber4.png";
            }
            public String goldNumber5(){
                return this +"/goldNumber5.png";
            }
            public String goldNumber6(){
                return this +"/goldNumber6.png";
            }
            public String goldNumber7(){
                return this +"/goldNumber7.png";
            }
            public String goldNumber8(){
                return this +"/goldNumber8.png";
            }
            public String goldNumber9(){
                return this +"/goldNumber9.png";
            }
            public String showCombo(){
                return this + "/comboXA.png";
            }
            public String showCombo1(){
                return this + "/combo.png";
            }
            public String showCombo2(){
                return this + "/comboX.png";
            }
            public String xRight(){
                return this + "/xRight.png";
            }
            public String xLeft(){
                return this + "/xLeft.png";
            }
            public String hp(){
                return this + "/hpWord.png";
            }
            public String slash(){
                return this + "/slash.png";
            }
            public String box2(){
                return this + "/box2.png";
            }
            public String box3(){
                return this + "/box3.png";
            }
            public String box4(){
                return this + "/box4.png";
            }
            public String fireRound(){
                return this + "/fireRound.png";
            }

            public String ball(){
                return this + "/ball.png";
            }

            public String actionDown() {
                return this + "/actionDown.png";
            }

            public String actionUp() {
                return this + "/actionUp.png";
            }

            public String actionDownRed() {
                return this + "/actionDownRed.png";
            }

            public String actionUpRed() {
                return this + "/actionUpRed.png";
            }
            public String box5() {
                return this + "/box5.png";
            }
            public String box6() {
                return this + "/box6.png";
            }
            public String imageMusic() {
                return this + "/imageMusic.png";
            }
             public String blood2() {
                return this + "/blood2.png";
            }
             public String home(){
                 return this + "/home.png";
             }
             public String again(){
                 return this + "/again.png";
             }
             public String menu(){
                 return this + "/menu.png";
             }
             public String outRing(){
                 return this + "/outRing.png";
             }
            public String categoryBlock(){
                return this + "/categoryBlock.png";
            }
        }

        public static class Backgrounds extends Flow {

            private Backgrounds(Flow flow) {
                super(flow, "/backgrounds");
            }
            public String lightStage() {
                return this + "/lightstage.jpg";
            }
            public String partyNight() {
                return this + "/partynight.png";
            }
            public String a0() {
                return this + "/A0.jpg";
            }
            public String a1() {
                return this + "/A1.jpg";
            }
            public String a2() {
                return this + "/A2.jpg";
            }
            public String a3() {
                return this + "/A3.jpg";
            }
            public String a4() {
                return this + "/A4.jpg";
            }
            public String a5() {
                return this + "/A5.jpg";
            }
            public String showMusicians() {
                return this + "/musicians.png";
            } 
            public String mainBackground() {
                return this + "/MatrixPanda.png";
            }
            public String mainPanda() {
                return this + "/Panda.png";
            }
            public String elc() {
                return this + "/elc.png";
            }

            public String box1() {
                return this + "/Box1.png";
            }

            public String settle1() {
                return this + "/settle1.png";
            }

            public String settle() {
                return this + "/settle.jpg";
            }

            public String backHome() {
                return this + "/backHome.png";
            }

            public String backMenu() {
                return this + "/backMenu.png";
            }

            public String playagain() {
                return this + "/playagain.png";
            }
            
        }
        
        public static class Effects extends Flow {
            private Effects(Flow flow) {
                super(flow, "/effects");
            }
            
            public static class FireRound extends Flow{
                private FireRound(Flow flow) {
                    super(flow, "/fireRound");
                }
                
                public String fireRound10() {
                    return this + "/fireRound10.png";
                }
                public String fireRound20() {
                    return this + "/fireRound20.png";
                }
                public String fireRound30() {
                    return this + "/fireRound30.png";
                }
                public String fireRound40() {
                    return this + "/fireRound40.png";
                }
                public String fireRound50() {
                    return this + "/fireRound50.png";
                }
                public String fireRound60() {
                    return this + "/fireRound60.png";
                }
                public String fireRound70() {
                    return this + "/fireRound70.png";
                }
                public String fireRound80() {
                    return this + "/fireRound80.png";
                }
                public String fireRound90() {
                    return this + "/fireRound90.png";
                }
                
            }
            
            public FireRound fireRound() {
                return new FireRound(this);
            }
        }
        

        public Actors actors() {
            return new Actors(this);
        }

        public Objs objs() {
            return new Objs(this);
        }

        public Backgrounds backgrounds() {
            return new Backgrounds(this);
        }
        
        public Effects effects() {
            return new Effects(this);
        }
    }

    public static class Sounds extends Flow {

        private Sounds() {
            super(new Resources(), "/sounds");
        }
    }

    public Imgs img() {
        return new Imgs();
    }

    public Sounds sound() {
        return new Sounds();
    }
}
