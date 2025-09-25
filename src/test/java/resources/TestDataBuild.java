package resources;

import pojo.googleMaps.AddPlace;
import pojo.googleMaps.Location;
import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace getAddPlace(String name,String language,String address) {
        AddPlace ap = new AddPlace();
        Location lc = new Location();
        lc.setLat(-38.383494);
        lc.setLng(34.427362);
        ap.setLocation(lc);
        ap.setAccuracy(50);
        ap.setAddress(address);
        ap.setName(name);
        ap.setWebsite("http://google.com");
        ap.setLanguage(language);
        ap.setPhone_number("(+91) 996 362 9618");
        List<String > types = new ArrayList<>();
        types.add("Shoe part");
        types.add("Shop");
        ap.setTypes(types);
        return ap;
    }
}
