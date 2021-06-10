package be.uantwerpen.be;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.*;

import static java.lang.Math.abs;

public class Visualise {
    public static TreeMap<Integer, String> nodeList = new TreeMap<>();
    public static List<Node> nodeLIST = new ArrayList<>();

    public static void main(String[] args) {
        JFrame f=new JFrame("Node GUI");
        final JTextField tf=new JTextField();
        final JLabel labelID = new JLabel("Node ID");
        final JLabel ipID = new JLabel("IP adress:");
        final JLabel filenameLabel = new JLabel("Filename:");
        filenameLabel.setBounds(10,30,60,20);
        ipID.setBounds(10,70,60,20);
        final JTextField tf3 = new JTextField();
        tf3.setBounds(70,30,115,20);
        labelID.setBounds(10,50,60,20);
        final JTextField tf2 = new JTextField();
        final JTextArea output = new JTextArea();
        output.setBounds(0,200, 390,180);
        tf.setBounds(70,50, 150,20);
        tf2.setBounds(70,70,150,20);
        // button C to remove a node
        JButton a = new JButton("Show nodes");
        a.setBounds(30,130,115,30);
        a.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nodeToTerminal;
                System.out.println("******************");
                nodeToTerminal ="******************\n";
                for(Node n:nodeLIST){
                    System.out.println("Node name :" + n.getHostname() + ". Node IP: "+n.getIP() +". NodeID: " + n.getID());
                    System.out.println("NextID/prevID: " + n.getNextID() + "," + n.getPrevID());
                    System.out.println("******************");
                    nodeToTerminal += ("Node name :" + n.getHostname() + ". Node IP: "+n.getIP() +". NodeID: " + n.getID()+"\n");
                }
                output.setText(nodeToTerminal);
                output.getCaret().setDot( Integer.MAX_VALUE );

            }
        });

        JButton e = new JButton("Add file");
        e.setBounds(250,10,100,30);
        e.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!tf3.getText().equals("")){
                    String test = tf.getText();
                    long max = 2147483647;
                    long min = -2147483647;
                    double result = (test.hashCode()+max)*(32768d/(max+abs(min)));
                    int test2 = (int) result;
                    if(nodeList.containsKey(test2)){
                        output.setText("Adding file to: "+test2);
                        for(Node n:nodeLIST){
                            if(test2 == n.getID()){
                                n.addFile(tf3.getText());
                            }
                        }
                    }

                }else{
                    output.setText("Cant add a filename with 0 length!");
                }
            }
        });
        JButton e2 = new JButton("Add RF");
        e2.setBounds(250,50,100,30);
        e2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!tf3.getText().equals("")){
                    String test = tf.getText();
                    long max = 2147483647;
                    long min = -2147483647;
                    double result = (test.hashCode()+max)*(32768d/(max+abs(min)));
                    int test2 = (int) result;
                    if(nodeList.containsKey(test2)){
                        output.setText("Adding file to: "+test2);
                        for(Node n:nodeLIST){
                            if(test2 == n.getID()){
                                n.addReplicatedFile(tf3.getText());
                            }
                        }
                    }

                }else{
                    output.setText("Cant add a filename with 0 length!");
                }
            }
        });
        JButton e3 = new JButton("Show File");
        e3.setBounds(250,90,100,30);
        e3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tf.getText().equals("")) return;
                String test = tf.getText();
                long max = 2147483647;
                long min = -2147483647;
                double result = (test.hashCode()+max)*(32768d/(max+abs(min)));
                int test2 = (int) result;
                if(nodeList.containsKey(test2)){
                    for(Node n:nodeLIST){
                        if(test2 == n.getID()){
                           if(n.getFiles().size()>0) {
                               output.setText("Files on node: ");
                               for (int i = 0; i < n.getFiles().size(); i++) {
                                   output.setText(output.getText() + "\n" + n.getFiles().get(i));

                               }
                           } else{
                               output.setText("No Files on this node.");
                           }
                            if(n.getReplicatedFiles().size()>0){
                                output.setText(output.getText() + "\n Replicated files on node: ");
                                for(int i=0;i<n.getReplicatedFiles().size();i++) {
                                    output.setText(output.getText() + "\n" + n.getFiles().get(i));
                                }
                          }else{
                                output.setText(output.getText() + "\n No Replicated Files on this node.");
                            }
                        }
                    }
                }
            }
        });


        JButton d = new JButton("Show configuration");
        d.setBounds(145,130,150,30);
        d.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tf.getText()!= null){
                   for(Node n:nodeLIST){
                       if(n.getHostname().equals(tf.getText())){
                           output.setText(" *****************");
                           System.out.println("*****************");
                           output.setText(output.getText() + "\n Details of node: ");
                           output.setText(output.getText() + "\n Hostname: " + n.getHostname());

                           output.setText(output.getText() + "\n Node ID: " + n.getID());

                           output.setText(output.getText() + "\n Next node: " + n.getNextID());
                           output.setText(output.getText() + "\n Previous node: " + n.getPrevID());
                           output.setText(output.getText() + "\n *****************");


                           System.out.println("Details of node: ");
                           System.out.println("Next node: " + n.getNextID());
                           System.out.println("Previous node: " + n.getPrevID());
                           System.out.println("*****************");
                            return;
                       }
                   }
                   output.setText("Node not found, cant give info");

                }
            }
        });
        JButton c=new JButton("Delete Node");
        c.setBounds(125,100,115,30);
        c.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                if(tf.getText().equals("") || tf2.getText().equals("")){
                    System.out.println("Empty field, not gonna remove node");
                    return;
                }
                String test = tf.getText();
                long max = 2147483647;
                long min = -2147483647;
                double result = (test.hashCode()+max)*(32768d/(max+abs(min)));
                int test2 = (int) result;
                if(nodeList.containsKey(test2)){
                    nodeList.remove(test2);
                    for(int i=0;i<nodeLIST.size();i++){
                        if(nodeLIST.get(i).getID() == test2) {
                            nodeLIST.remove(i);
                            break;
                        }
                    }
                    for(Node n:nodeLIST){
                        n.updateNextPrevID(nodeList);
                    }
                    System.out.println("Node removed.");
                    output.setText("\n Node removed");
                    output.getCaret().setDot( Integer.MAX_VALUE );

                } else{
                    System.out.println("Cant find node, wont remove!");
                    output.setText("\n Cant find node, wont remove");
                    output.getCaret().setDot( Integer.MAX_VALUE );

                }
            }
        });
        // Button B to add a node
        JButton b=new JButton("Add Node");
        b.setBounds(30,100,95,30);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){


                if(tf.getText().equals("") || tf2.getText().equals("")){
                    System.out.println("Empty field, not gonna add node");
                    output.setText("\n Empty field, not gonna add node");
                    output.getCaret().setDot( Integer.MAX_VALUE );

                    return;
                }
                String test = tf.getText();
                long max = 2147483647;
                long min = -2147483647;
                double result = (test.hashCode()+max)*(32768d/(max+abs(min)));
                int test2 = (int) result;
                if(nodeList.containsKey(test2)){
                    System.out.println("Already exists, cant add");
                    output.setText("\n Already exists, cant add");
                    output.getCaret().setDot( Integer.MAX_VALUE );


                } else{
                    Node n = new Node(tf.getText(),tf2.getText(),nodeList);
                    System.out.println("Node added.");
                    System.out.println("NextID and PrevID: " + n.getNextID() + ", " + n.getPrevID());
                    output.setText("\n Node added");
                    output.setText(output.getText() + "\n NextID and PrevID: " + n.getNextID() + ", " + n.getPrevID());
                    output.getCaret().setDot( Integer.MAX_VALUE );


                    nodeLIST.add(n);
                    for(Node x:nodeLIST){
                        x.updateNextPrevID(nodeList);
                    }
                    for(Map.Entry<Integer,String> entry : nodeList.entrySet()) {
                        String key = entry.getValue();
                        Integer value = entry.getKey();

                        System.out.println(value + " => " + key);
                        output.setText(output.getText() + "\n Value: " + value + " => key:  " + key);
                        output.getCaret().setDot( Integer.MAX_VALUE );


                    }
                }
            }
        });
        f.add(e);
        f.add(ipID);f.add(labelID);f.add(d);f.add(output);f.add(a);f.add(b);f.add(c);f.add(tf);f.add(tf2);
        f.add(e2);f.add(e3);f.add(tf3);f.add(filenameLabel);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);

    }

}