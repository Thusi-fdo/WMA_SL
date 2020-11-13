package Database;

import Code.Resident;

public class Register_Query {
private Database_Connection db;
    
    public Register_Query(){
        db=Database_Connection.getSingleInstance();
    }
    
    public String[] getAreaList(){
        
        String Query = "select * from area";
        String QueryCount="select count(*) from area";
        
        return db.GetArea(Query,QueryCount);    
    }
    
    public String[] getSubAreaList(int Subarea_Index) {
    	return db.GetSubArea(Subarea_Index);
    }
    
    public boolean CreateResident(Resident r) {
    	String query = "INSERT INTO `resident`(`NIC`, `Name`, `Email`, `Password`, `Address`, `City`, `Subarea`) VALUES ('"
    			+r.getNIC()+"','"+r.getResidentName()+"','"+r.getEmail()+"','"+r.getPassword()+"','"+r.getAddress()+"','"+r.getCity()+"','"+r.getSubarea()+"')";
    	
    	//String Query="insert into Customer2019(Name,Telephone,email,Service,Price,Status,orderedDate)values('"+c.getCustName()+
       // "','"+c.getEmail()+"','"+c.getTel()+"',"+o.getOrderID()+","+o.getPrice()+",'Incomplete','"+dateFormat.format(new Date())+"')";
    	
    	return db.ExecutionQuery(query);
		
    }

}
