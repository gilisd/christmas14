package be.digan.learning.christmas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ChristmasBusiness {
    @Autowired
    PresentDao presentDao;

    public List<String> getPresentsFor(String name) throws UnknownUserException {
        List<Giver> data = presentDao.getData();
        HashMap<String, Integer> presentList = new HashMap<String, Integer>();
        for (Giver giver : data) {
            for (Present present : giver.getPresents()) {
                if (present.getForWhom().equals(name)) {
                    String what = present.getWhat();
                    if (presentList.containsKey(what)) {
                        presentList.put(what, presentList.get(what) + 1);
                    } else {
                        presentList.put(what, 1);
                    }
                }
            }
        }
        ArrayList<String> result = new ArrayList<String>();
        for (String present : presentList.keySet()) {
            result.add(presentList.get(present) + " " + present);
        }
        if (result.size() == 0) {
            boolean isGiver = false;
            // No reciever
            for (Giver giver : data) {
                if (giver.getName().equals(name)) {
                    isGiver = true;
                }
            }
            if (!isGiver) {
                throw new UnknownUserException();
            }
        }
        return result;
    }
}
