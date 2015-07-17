//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import Aura.Shared.Util.IOrderedDictionary;
import CS2JNet.JavaSupport.language.RefSupport;
import CS2JNet.System.ArgumentException;
import CS2JNet.System.Collections.LCC.CSList;
import CS2JNet.System.Collections.LCC.ICollection;
import CS2JNet.System.Collections.LCC.IEnumerable;
import CS2JNet.System.Collections.LCC.IEnumerator;
import java.util.HashMap;
import java.util.Map.Entry;

/**
* A dictionary that remembers the order that keys were first inserted. If a new entry overwrites an existing entry, the original insertion position is left unchanged. Deleting an entry and reinserting it will move it to the end.
* The type of keys. Musn't be 
*  {@link #int}
* The type of values.
*/
public final class OrderedDictionary <TKey, TValue>  implements IOrderedDictionary<TKey,TValue>
{
    /**
    * An unordered dictionary of key pairs.
    */
    private final HashMap<TKey,TValue> fDictionary;
    /**
    * The keys of the dictionary in the exposed order.
    */
    private final CSList<TKey> fKeys;
    /**
    * A dictionary that remembers the order that keys were first inserted. If a new entry overwrites an existing entry, the original insertion position is left unchanged. Deleting an entry and reinserting it will move it to the end.
    */
    public OrderedDictionary() throws Exception {
        if (TKey.class == int.class)
            throw new NotSupportedException("Integer-like type is not appropriate for keys in an ordered dictionary - accessing values by key or index would be confusing.");
         
        fDictionary = new HashMap<TKey,TValue>();
        fKeys = new CSList<TKey>();
    }

    /**
    * The number of elements in the dictionary.
    */
    public int getCount() throws Exception {
        return fDictionary.size();
    }

    /**
    * This dictionary is not read only.
    */
    public boolean getIsReadOnly() throws Exception {
        return false;
    }

    /**
    * The keys of the dictionary, in order.
    */
    public ICollection<TKey> getKeys() throws Exception {
        return fKeys.AsReadOnly();
    }

    /**
    * The values in the dictionary, in order.
    */
    public ICollection<TValue> getValues() throws Exception {
        return fKeys.Select(/* [UNSUPPORTED] to translate lambda expressions we need an explicit delegate type, try adding a cast "(key) => {
            return fDictionary.get(key);
        }" */).ToArray();
    }

    /**
    * The value at the given index.
    */
    public TValue get___idx(int index) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ key = fKeys.get(index);
        return fDictionary.get(key);
    }

    public void set___idx(int index, TValue value) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ key = fKeys.get(index);
        fDictionary.put(key, value);
    }

    /**
    * The value under the given key. New entries are added at the end of the order. Updating an existing entry does not change its position.
    */
    public TValue get___idx(TKey key) throws Exception {
        return fDictionary.get(key);
    }

    public void set___idx(TKey key, TValue value) throws Exception {
        if (!fDictionary.containsKey(key))
        {
            // New entries are added at the end of the order.
            fKeys.add(key);
        }
         
        fDictionary.put(key, value);
    }

    /**
    * Find the position of an element by key. Returns -1 if the dictionary does not contain an element with the given key.
    */
    public int indexOf(TKey key) throws Exception {
        return fKeys.IndexOf(key);
    }

    /**
    * Remove the element at the given index.
    */
    public void removeAt(int index) throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ key = fKeys.get(index);
        fDictionary.remove(key);
        fKeys.remove((int));
    }

    /**
    * Test whether there is an element with the given key.
    */
    public boolean containsKey(TKey key) throws Exception {
        return fDictionary.containsKey(key);
    }

    /**
    * Try to get a value from the dictionary, by key. Returns false if there is no element with the given key.
    */
    public boolean tryGetValue(TKey key, RefSupport<TValue> value) throws Exception {
        RefSupport<TValue> refVar___0 = new RefSupport<TValue>();
        resVar___0 = fDictionary.TryGetValue(key, refVar___0);
        value.setValue(refVar___0.getValue());
        return resVar___0;
    }

    /**
    * Insert an element at the given index.
    */
    public void insert(int index, TKey key, TValue value) throws Exception {
        // Dictionary operation first, so exception thrown if key already exists.
        fDictionary.put(key, value);
        fKeys.add(index, key);
    }

    /**
    * Add an element to the dictionary.
    */
    public void add(TKey key, TValue value) throws Exception {
        // Dictionary operation first, so exception thrown if key already exists.
        fDictionary.put(key, value);
        fKeys.add(key);
    }

    /**
    * Add an element to the dictionary.
    */
    public void add(Entry<TKey,TValue> pair) throws Exception {
        add(pair.getKey(),pair.getValue());
    }

    /**
    * Test whether the dictionary contains an element equal to that given.
    */
    public boolean contains(Entry<TKey,TValue> pair) throws Exception {
        return fDictionary.Contains(pair);
    }

    /**
    * Remove a key-value pair from the dictionary. Return true if pair was successfully removed. Otherwise, if the pair was not found, return false.
    */
    public boolean remove(Entry<TKey,TValue> pair) throws Exception {
        if (contains(pair))
        {
            remove(pair.getKey());
            return true;
        }
         
        return false;
    }

    /**
    * Remove the element with the given key key. If there is no element with the key, no exception is thrown.
    */
    public boolean remove(TKey key) throws Exception {
        boolean wasInDictionary = fDictionary.remove(key);
        boolean wasInKeys = fKeys.remove(key);
        Contract.Assume(wasInDictionary == wasInKeys);
        return wasInDictionary;
    }

    /**
    * Delete all elements from the dictionary.
    */
    public void clear() throws Exception {
        fDictionary.clear();
        fKeys.clear();
    }

    /**
    * Copy the elements of the dictionary to an array, starting at at the given index.
    */
    public void copyTo(Entry<TKey,TValue>[] array, int index) throws Exception {
        if (array == null)
            throw new ArgumentNullException("array");
         
        if (index < 0)
            throw new ArgumentOutOfRangeException("index", "Must be greater than or equal to zero.");
         
        if (index + fDictionary.size() > array.length)
            throw new ArgumentException("Array is too small", "array");
         
        for (KeyValuePair<TKey, TValue> pair : this.entrySet())
        {
            array[index] = pair;
            index++;
        }
    }

    private IEnumerable<Entry<TKey,TValue>> enumerate() throws Exception {
        for (TKey key : fKeys)
        {
            /* [UNSUPPORTED] 'var' as type is unsupported "var" */ value = fDictionary.get(key);
        
        }
    }

    IEnumerator<Entry<TKey,TValue>> iEnumerable<KeyValuePair<TKey,TValue>>___GetEnumerator() throws Exception {
        return enumerate().getEnumerator();
    }

    IEnumerator iEnumerable___GetEnumerator() throws Exception {
        return enumerate().getEnumerator();
    }

    /**
    * Conditions that should be true at the end of every public method.
    */
    private void objectInvariant() throws Exception {
        Contract.Invariant(fDictionary.size() == fKeys.size(), "Unordered dictionary and ordered key list should be the same length.");
    }

}


