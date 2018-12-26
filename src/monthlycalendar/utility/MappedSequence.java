package monthlycalendar.utility;

import monthlycalendar.utility.exception.InvalidKeyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MappedSequence<Tag, Elem> implements Iterable<Elem> {
    private final Map<Tag, List<Elem>> map_ = new HashMap<>();

    public void push(Tag tag, Elem e) {
        if(!map_.containsKey(tag)) {
            map_.put(tag, new ArrayList<Elem>());
        }

        map_.get(tag).add(e);
    }
    public List<Elem> get(Tag tag) {
        return map_.get(tag);
    }
    public Elem getAnyElem(Tag tag) {
        if(!contains(tag)) {
            throw new InvalidKeyException();
        }

        return map_.get(tag).get(0);
    }

    public boolean contains(Tag tag) {
        return map_.containsKey(tag);
    }

    @Override
    public Iterator<Elem> iterator() {
        List<Elem> work = new ArrayList<>();
        for(List<Elem> list: map_.values()) {
            work.addAll(list);
        }

        return work.iterator();
    }
}
