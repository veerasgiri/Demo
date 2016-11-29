
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@EnableAutoConfiguration
public class SatisfactionCalculator {

    @RequestMapping("/")
    String home() {
        return "To get the satisfaction please provide the timeslice as parameter <br>example: <a href=\"http://localhost:8080/getData?TimeSlice=100\">http://hostname:portno/getData?TimeSlice=100</a>";
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
    String getData(@RequestParam("TimeSlice") String itemid) {
    	
    	
    	if(Integer.parseInt(itemid)<=0){
    		return "Enter a Valid Timeslice";
    	}
    	
    	Satisfaction satisfaction = new Satisfaction();
    	long val=satisfaction.checkSatisfaction(Integer.parseInt(itemid));
//        return "Data!"+val ;
        Object ItemsToEatAre= "The Maximum Satisfaction that can be achieved in given time <b>" +itemid+"</b> is <b>"+ val +"</b> <br>";
    	return ItemsToEatAre.toString() + "<br>"+satisfaction.ItemsToEat.toString();
        
        
    }
    //http://localhost:8080/getData?data=002
   

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SatisfactionCalculator.class, args);
    }

}
