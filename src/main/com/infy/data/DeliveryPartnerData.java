package main.com.infy.data;


import main.com.infy.models.DeliveryPartner;
import main.com.infy.util.JsonFileUtil;
import main.com.infy.util.FilePaths;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;

public class DeliveryPartnerData {
    private static final Type DELIVERY_PARTNER_LIST_TYPE = new TypeToken<List<DeliveryPartner>>() {}.getType();
    private static final JsonFileUtil<DeliveryPartner> jsonUtil = new JsonFileUtil<>(FilePaths.DELIVERY_PARTNER_FILE, DELIVERY_PARTNER_LIST_TYPE);

    private static List<DeliveryPartner> partnerList = jsonUtil.readData();

    public static List<DeliveryPartner> getAllDeliveryPartners() {
        return partnerList;
    }

    public static void addDeliveryPartner(DeliveryPartner dp) {
        partnerList.add(dp);
        jsonUtil.writeData(partnerList);
    }

    public static int getDeliveryPartnerCount() {
        return partnerList.size();
    }

    public static DeliveryPartner findByEmail(String email) {
        for (DeliveryPartner d : partnerList) {
            if (d.getEmailId().equalsIgnoreCase(email)) {
                return d;
            }
        }
        return null;
    }
    public static boolean deleteDeliveryPartnerById(String partnerId){
        List<DeliveryPartner> partners = getAllDeliveryPartners();
        boolean removed = partners.removeIf(p ->p.getPatnerId().equals(partnerId));
        if(removed){
            jsonUtil.writeData(partners);
        }
        return removed;
    }
    public static void saveAllDeliveryPartnersToFile(){
        try (Writer writer = new FileWriter("data/delivery_partners.json")){
            new GsonBuilder().setPrettyPrinting().create().toJson(partnerList,writer);
        }catch(IOException e){
            System.out.println("Error Saving changes!.."+ e.getMessage());
        }
    }
    public static String generateNextDeliveryPartnerId() {
        List<DeliveryPartner> allPartners = getAllDeliveryPartners();
        int max = 0;
        for (DeliveryPartner dp : allPartners) {
            try {
                int num = Integer.parseInt(dp.getPatnerId().replace("PAT", ""));
                if (num > max) max = num;
            } catch (Exception e) {
                // Ignore malformed IDs
            }
        }
        return String.format("PAT%03d", max + 1);
    }
}
