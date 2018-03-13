import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by windons8 on 2018/2/26.
 */
class SimpleEntry <K,V> implements Map.Entry<K,V>, java.io.Serializable {

    private final K key;
    private V value;
    public SimpleEntry(K key,V value){
        this.key=key;
        this.value=value;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }
    public V setValue(V value){
        V oldValue=this.value;
        this.value=value;
        return oldValue;
    }
    public boolean equals(Object o){
        if(o ==this){
            return true;
        }
        if(o.getClass()== SimpleEntry.class){
            SimpleEntry se=(SimpleEntry)o;
            return se.getKey().equals(getKey());
        }
        return false;
    }

    public int hashcode(){
        return key==null?0:key.hashCode();
    }
    public String toString(){
        return key+"="+value;
    }
}

public class Set2Map<K,V> extends HashSet<SimpleEntry<K,V>>{
    public void clear(){
        super.clear();
    }
    public boolean containkey(K key){
        return super.contains(new SimpleEntry<K,V>(key,null));
    }

    public boolean containValue(Object value){
        for (SimpleEntry<K,V> se :this){
            if (se.getValue().equals(value))
                return true;
        }
        return false;
    }

    public V get(Object key){
        for (SimpleEntry<K,V> se :this){
            if (se.getKey().equals(key))
                return se.getValue();
        }
        return null;
    }

    public V put(K key,V value){
        add(new SimpleEntry<K,V>(key,value));
        return value;
    }

    public void  putAll(Map<? extends K,? extends V> m){

        for (K key: m.keySet()){
            add(new SimpleEntry<K,V>(key,m.get(key)));
        }
    }

    public V removeEntry(Object key) {
        for (Iterator<SimpleEntry<K,V>> it = this.iterator(); it.hasNext();){
            SimpleEntry<K,V> en=(SimpleEntry<K,V>) it.next();
            if (en.getKey().equals(key)){
                V v=en.getValue();
                it.remove();
                return v;
            }
        }
        return null;
    }

    public int size(){
        return super.size();
    }





    public static void main(String[] args){
        Set2Map<String,Integer> scores=new Set2Map<>();
        scores.put("语文",98);
        scores.put("属性",56);
        scores.put("士大夫",52);
        scores.put("语文",87);

        HashMap<String ,Integer> xiaoming=new HashMap<>();
        xiaoming.put("x大哥",45);
        xiaoming.put("x士大夫",78);
        xiaoming.put("x收到",56);
        scores.putAll(xiaoming);
        System.out.println(scores);

        System.out.println(scores.size());

        scores.removeEntry("语文");
        System.out.println("删除\"语文\" 后："+scores);

        System.out.println("是否包含52："+scores.containValue(52));
        scores.forEach(x->System.out.println(x));

        System.out.println(1000>>>4);


    }
}


