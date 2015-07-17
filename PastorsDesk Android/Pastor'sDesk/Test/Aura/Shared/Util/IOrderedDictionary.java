//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:45 AM
//

package Aura.Shared.Util;

import java.util.Map;

// https://gist.github.com/matt-hickford/5137384
/**
* A dictionary that remembers the order that keys were first inserted.
* If a new entry overwrites an existing entry, the original insertion
* position is left unchanged. Deleting an entry and reinserting it
* will move it to the end.
* The type of keysThe type of values
*/
public interface IOrderedDictionary <TKey, TValue>  extends Map<TKey,TValue>
{
    /**
    * The value of the element at the given index.
    */
    TValue get___idx(int index) throws Exception ;

    void set___idx(int index, TValue value) throws Exception ;

    /**
    * Find the position of an element by key. Returns -1 if the dictionary does not contain an element with the given key.
    */
    int indexOf(TKey key) throws Exception ;

    /**
    * Insert an element at the given index.
    */
    void insert(int index, TKey key, TValue value) throws Exception ;

    /**
    * Remove the element at the given index.
    */
    void removeAt(int index) throws Exception ;

}


