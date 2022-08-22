package model;
// class for the InHouse radio button
public class InHouse extends Part {

    private static int machineID;


    public InHouse(int id, String name, double price,int stock, int min, int max, int machineID){
        super(id,name,price,stock,min,max);
        this.machineID=machineID;
    }



    public void setMachineID(int machineID){
        this.machineID = machineID;
    }
    public static int getMachineID(){
        return machineID;
    }
}

