// Name: Elijah Valine
// Email: valine@wisc.edu
// Team: blue
// Group: HA
// TA: Hang Y
// Lecturer: Florian


import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT {

    private LinkedList<Entry> keys;
    private LinkedList<Entry>[] mEntry;
    private final static double LOAD_CAPACITY = 0.85;

    private int currentIndex;
    public HashTableMap() {

        keys = new LinkedList<Entry>();

        currentIndex = 0;
        mEntry = new LinkedList[10];
    }
    public HashTableMap(int capacity) {
        keys = new LinkedList<Entry>();

        currentIndex = 0;
        mEntry = new LinkedList[capacity];
    }

    @Override
    public boolean put(Object key, Object value) {

        Entry current = new Entry(key, value, mEntry.length);
        int before = keys.size();
        int index = current.getIndex();

        if (!(index >= mEntry.length)){
            if (mEntry[index] == null){
                mEntry[index] = new LinkedList<Entry>();
                mEntry[index].add(current);
                keys.add(current);
            }
            else{
                for (int i = 0; i < mEntry[index].size(); i ++){
                    if(mEntry[index].get(i).getKey().hashCode() == key.hashCode()) {
                        return false;
                    }
                }

                mEntry[index].add(current);
                keys.add(current);

            }
        }


        double x = (double)keys.size()/(double)mEntry.length;
        if (x >= LOAD_CAPACITY){
            rehashy();
        }
        if (keys.size() == before+1){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public Object get(Object key) throws NoSuchElementException {
        int hash = key.hashCode();
        int size = mEntry.length;
        int mod = hash%size;
        int index = Math.abs(mod);
        if (mEntry[index] != null) {
            if (mEntry[index].size() == 1) {
                if (mEntry[index].get(0).getKey().hashCode() == key.hashCode()) {
                    return mEntry[index].get(0).getValue();
                } else {
                    throw new NoSuchElementException();
                }
            } else {
                for (int i = 0; i < mEntry[index].size(); i++) {
                    if (mEntry[index].get(i).getKey().hashCode() == key.hashCode()) {
                        return mEntry[index].get(i).getValue();

                    }
                }
                throw new NoSuchElementException();
            }
        }
        else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean containsKey(Object key) {
        try  {
            if (get(key) != null){
                return true;
            }
        }
        catch(NoSuchElementException e){
            return false;
        }
        return false;
    }

    @Override
    public Object remove(Object key) {
        int hash = key.hashCode();
        int size = mEntry.length;
        int mod = hash%size;
        int index = Math.abs(mod);
        for (int i = 0; i < keys.size(); i ++){
            Entry current = keys.get(i);
            if (current.getKey().equals(key)){
                keys.remove(i);
            }
        }
        if (mEntry[index] != null) {
            if (mEntry[index].size() == 1) {
                if (mEntry[index].get(0).getKey().hashCode() == key.hashCode()) {
                    Object temp = mEntry[index].get(0).getValue();
                    mEntry[index].remove(0);
                    return temp;
                } else {
                    return null;
                }
            } else {
                for (int i = 0; i < mEntry[index].size(); i++) {
                    if (mEntry[index].get(i).getKey().hashCode() == key.hashCode()) {
                        Object temp = mEntry[index].get(i).getValue();
                        mEntry[index].remove(i);
                        return temp;

                    }
                }
                return null;
            }
        }
        else{
            return null;
        }
    }

    @Override
    public void clear() {
        mEntry = new LinkedList[10];
    }
    private void rehashy(){
        LinkedList<Entry>[] tempy = mEntry;
        mEntry = new LinkedList[tempy.length*2];

        for (int i = 0; i< keys.size(); i++){
            keys.get(i).rehash(mEntry.length);
        }
        for (int i = 0; i < keys.size(); i++) {

           Entry current = keys.get(i);
           int index = current.getIndex();

            if (mEntry[index] == null){
                mEntry[index] = new LinkedList<Entry>();
                mEntry[index].add(current);
            }
            else{
                mEntry[index].add(current);
            }
        }
    }
}

class Entry<K, V> {


    private V mValue;
    private int mKey;
    private K realKey;

    public Entry( K key,V value, int size){
        realKey = key;
        mValue = value;
        int hash = key.hashCode();
        int mod = hash%size;
        int index = Math.abs(mod);
        mKey = index;
    }
    public int getIndex(){
        return mKey;
    }
    public Object getKey(){
        return realKey;
    }
    public Object getValue(){
        return mValue;
    }
    public void rehash(int size){

        int hash = realKey.hashCode();
        int mod = hash%size;
        int index = Math.abs(mod);
        mKey = index;
    }
    public void changeIndex(int index){
        mKey = index;
    }

    @Override
    public String toString(){

        return mValue.toString();

    }
}
