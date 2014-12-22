package be.digan.learning.christmas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ChristmasBusiness {
    public static final String EURO = "euro";
    @Autowired
    PresentDao presentDao;

    public List<String> getPresentsFor(String name) throws UnknownUserException {
        List<Giver> data = presentDao.getData();
        ArrayList<String> result = getPresentsFromData(name, data);
        if ((!isReciever(result)) && (!isGiver(name, data))) {
            throw new UnknownUserException();
        }
        return result;
    }

    private boolean isReciever(ArrayList<String> recievedPresents) {
        return (recievedPresents.size() != 0);
    }

    private boolean isGiver(String name, List<Giver> data) {
        boolean isGiver = false;
        for (Giver giver : data) {
            if (giver.getName().equals(name)) {
                isGiver = true;
            }
        }
        return isGiver;
    }

    private ArrayList<String> getPresentsFromData(String name, List<Giver> data) {
        HashMap<String, Integer> presentList = getRawPresentList(name, data);
        ArrayList<String> result = new ArrayList<String>();
        for (String present : presentList.keySet()) {
            result.add(presentList.get(present) + " " + present);
        }
        return result;
    }

    private HashMap<String, Integer> getRawPresentList(String name, List<Giver> data) {
        HashMap<String, Integer> presentList = new HashMap<String, Integer>();
        for (Giver giver : data) {
            for (Present present : giver.getPresents()) {
                if (present.getForWhom().equals(name)) {
                    String what = present.getWhat();
                    if (what.endsWith(EURO)) {
                        int amount = Integer.parseInt(what.substring(0, what.length()-5));
                        if (presentList.containsKey(EURO)) {
                            presentList.put(EURO, presentList.get(EURO) + amount);
                        } else {
                            presentList.put(EURO, amount);
                        }
                    } else {
                        if (presentList.containsKey(what)) {
                            presentList.put(what, presentList.get(what) + 1);
                        } else {
                            presentList.put(what, 1);
                        }
                    }
                }
            }
        }


        return presentList;
    }
}
