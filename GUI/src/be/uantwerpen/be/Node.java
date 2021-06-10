package be.uantwerpen.be;

import java.util.*;

import static java.lang.Math.abs;

public class Node {
    private String hostname;
    private int ID, nextID, prevID;
    private String IP;
    private List<String> files = new ArrayList<>(), replicatedFiles = new ArrayList<>();

    /**
     * We receive a message to update next/prevID
     * @param nodeList
     */
    void updateNextPrevID(TreeMap<Integer, String> nodeList){
        if(nodeList.size() ==1){
            prevID=this.ID;
            nextID=this.ID;
        }
        if(nodeList.size()==2){
            if(nodeList.higherKey(this.ID) != null){
                // This means there is a higher Key than current -> updoot
                nextID = nodeList.higherKey(this.ID);
                prevID = nextID;
            } else{
                nextID = nodeList.firstKey();
                prevID= nextID;
            }
        }
        if(nodeList.size() > 2){
            if(nodeList.higherKey(this.ID) != null) nextID = nodeList.higherKey(this.ID);
            else{
                //Higher key does not exist -_> lowest key
                nextID = nodeList.firstKey();
            }
            if(nodeList.lowerKey(this.ID) != null) prevID = nodeList.lowerKey(this.ID);
            else{
                //Lower key does not exist -> highest key
                prevID = nodeList.lastKey();
            }
        }

    }

    public List<String> getFiles() {
        return files;
    }

    public List<String> getReplicatedFiles() {
        return replicatedFiles;
    }

    public Node(String hostname, String IP, TreeMap<Integer, String> nodeList){
        this.hostname = hostname;
        this.ID = hashCode();
        this.IP = IP;
        if(nodeList.size() == 0){
            prevID = this.ID;
            nextID = this.ID;
        }else if(nodeList.size() == 1){
            prevID = nodeList.firstKey();
            nextID = prevID;
        }else{


            if(nodeList.higherKey(this.ID) != null) nextID = nodeList.higherKey(this.ID);
            else{
                //Higher key does not exist -_> lowest key
                nextID = nodeList.firstKey();
            }
            if(nodeList.lowerKey(this.ID) != null) prevID = nodeList.lowerKey(this.ID);
            else{
                //Lower key does not exist -> highest key
                prevID = nodeList.lastKey();
            }

        }
        nodeList.put(this.ID, this.IP);


        if(nodeList.higherKey(this.ID) != null)     nextID = nodeList.higherKey(this.ID);
        if(nodeList.lowerKey(this.ID) != null)      prevID = nodeList.lowerKey(this.ID);
    }


    public void addFile(String file){
        files.add(file);
    }
    public void addReplicatedFile(String file){
        replicatedFiles.add(file);
    }
    public void removeFile(String file){

    }
    public int getNextID() {
        return nextID;
    }

    public int getPrevID() {
        return prevID;
    }

    /**
     * Sets the hostname
     * @param hostname name of the host, should not be visible to world
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Sets the IP address if needed
     * @param IP ip adress
     */
    public void setIP(String IP) {
        this.IP = IP;
    }

    /**
     * Returns the hostname e.g. Robbe Gerben Koen
     * @return Name of the host
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Returns the ID which is the hash value of the node
     * @return hash value of node
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the IP value of the node
     * @return IP String
     */
    public String getIP() {
        return IP;
    }


    public void setID(String id){
        this.ID = hashCode();

    }
    /**
     * hashCode gets the name of the node as input (ip adress) and returns the hashcode value as output
     * @return hashCode integer.
     */
    @Override
    public int hashCode() {
        long max = 2147483647;
        long min = -2147483647;

        double result = (hostname.hashCode()+max)*(32768d/(max+abs(min)));

        return (int) result;
    }
}