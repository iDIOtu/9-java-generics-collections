package com.example.task02;

import java.io.File;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;
    private List<E> list;

    public SavedList(File file) {
        this.file = file;

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                list = (ArrayList<E>) ois.readObject();
            }catch (Exception ignored){}
        }
        else {
            list = new ArrayList<>();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E el = list.set(index, element);
        Write();
        return el;

    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        Write();
    }

    @Override
    public E remove(int index) {
        E el = list.remove(index);
        Write();
        return el;
    }

    private void Write() {
        try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(file))){
            ois.writeObject(list);
        }catch (Exception ignored){}
    }
}
