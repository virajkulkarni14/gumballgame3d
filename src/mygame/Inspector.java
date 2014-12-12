/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

public class Inspector extends HandlerObserver {

    private HandlerObserver successor = null;

  //  public Inspector(ConcreteSubject sub) {
       // super(sub);
   // }
     public Inspector() {
        super();
   }

//    public void update() {
//        
//	}
    public void handleRequest(String request) {
        int cost = 50;
        String txtB = "Enter more " + (cost - Main.amount)+" cents";

        if (Main.amount < cost) {
            Main.pickerflag=false;
            Main.app.getRootNode().attachChild(Main.app.g);
            Main.app.txt.setText(txtB);
            Main.app.getRootNode().attachChild(Main.app.txt);
            System.out.println(txtB);
            GumballMachine.fakeq=true;
            Main.audio_fake.playInstance();

        } else {
            if (successor != null) {
                successor.handleRequest(request);
            }
        }
    }

    public void setSuccessor(HandlerObserver next) {
        this.successor = next;
    }
}
