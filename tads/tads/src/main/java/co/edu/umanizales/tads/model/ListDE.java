package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListDE {
private NodeDE headDE;
private int size ;
private int countDE;

    public ListDE() {

    }

    public void addDE(Pet pet) {
        NodeDE newNodeDE = new NodeDE(pet);
        NodeDE temp = headDE;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(newNodeDE);
        newNodeDE.setPrev(temp);
        size += (headDE == null) ? 1 : 0;
        headDE = (headDE == null) ? newNodeDE : headDE;
    }
    //get Kid By id

    public Pet getKidByidDE(String identification) {
        NodeDE temp = headDE;
        if (headDE != null) {
            while (temp != null) {
                temp.getNext();
                while (!temp.getData().getIdentificationDE().equals(identification)) {
                    temp.getNext();
                }
                temp.getData();

            }
        }
        Pet pet =new Pet(temp.getData().getIdentificationDE(),temp.getData().getName(),temp.getData().getAgeDE());
        return pet;

    }

    //get in pos by id
    public int getPosByIdDE(String id) {
        NodeDE temp = headDE;
        int acum = 0;
        while (temp != null && !temp.getData().getIdentificationDE().equals(id)) {
            temp = temp.getNext();
            acum++;
        }
        return (temp != null) ? acum : -1;
    }
    //añadir por posicion
    public void addbypositionDE(Pet pet,int position){
        NodeDE newNodoDE = new NodeDE(pet);
        if (position ==0){
            newNodoDE.setNext(headDE);
            headDE = newNodoDE;
        }else {
            NodeDE act = headDE;
            for (int i = 1; i < position - 1;i++){
                act = act.getNext();
            }
            newNodoDE.setNext(act.getNext());
            act.setNext(newNodoDE);
        }
    }
    //metodo para añadir nuevo nodo y nuevo niño en un posicion
    public void addInposDE(Pet pet, int position) {
        NodeDE temp = headDE;
        for (int i = 0; i < position; i++) {
            temp = temp.getNext();
        }
        NodeDE newNodeDE = new NodeDE(pet);
        temp.setNext(newNodeDE);
    }
    //1 invertir lista

    public void invertDE() {
        if (this.headDE != null) {
            ListDE listCp = new ListDE();
            NodeDE temp = this.headDE;
            while (temp != null) {
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.headDE = listCp.getHeadDE();
            this.headDE.setPrev(null);
            NodeDE temp2 = this.headDE;
            while (temp2.getNext() != null) {
                temp2.getNext().setPrev(temp2);
                temp2 = temp2.getNext();
            }
        }
    }
    // 2 adicionar al inicio

    public void addToStart(Pet pet) {
        if (headDE != null) {
            NodeDE newNodeDE = new NodeDE(pet);
            newNodeDE.setNext(headDE);
            headDE.setPrev(newNodeDE);
            headDE = newNodeDE;
        } else {
            headDE = new NodeDE(pet);
        }
        size++;
    }

    public void addToEnd(Pet pet){
        if (headDE != null) {
            NodeDE newNodeDE = new NodeDE(pet);
            NodeDE temp = headDE;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNodeDE);
            newNodeDE.setPrev(temp);
        }else {
            headDE = new NodeDE(pet);
        }
        size++;
    }
    //3
    public void interposeFemaleandMale() {
        if (headDE != null) {
            ListDE listM = new ListDE();
            ListDE listF = new ListDE();
            NodeDE temp = headDE;
            while (temp != null) {
                if (temp.getData().getGenderDE() == 'M') {
                    listM.addToEnd(temp.getData());
                } else if (temp.getData().getGenderDE() == 'F') {
                    listF.addToEnd(temp.getData());
                }
                temp = temp.getNext();
            }
            headDE = null;
            NodeDE tempM = listM.getHeadDE();
            NodeDE tempF = listF.getHeadDE();
            while (tempM != null && tempF != null) {
                if (headDE == null) {
                    headDE = tempM;
                    tempM = tempM.getNext();
                    headDE.setPrev(null);
                } else {
                    headDE.setNext(tempM);
                    tempM.setPrev(headDE);
                    headDE = headDE.getNext();
                    tempM = tempM.getNext();
                }
                headDE.setNext(tempF);
                tempF.setPrev(headDE);
                headDE = headDE.getNext();
                tempF = tempF.getNext();
            }
            while (tempM != null) {
                headDE.setNext(tempM);
                tempM.setPrev(headDE);
                headDE = headDE.getNext();
                tempM = tempM.getNext();
            }
            while (tempF != null) {
                headDE.setNext(tempF);
                tempF.setPrev(headDE);
                headDE = headDE.getNext();
                tempF = tempF.getNext();
            }
            while (headDE.getPrev() != null) {
                headDE = headDE.getPrev();
            }
        }
    }
// 4 eliminar por edad dada
public void deleteByAge(byte ageDE) {
    NodeDE temp = headDE;
    ListDE listDE1 = new ListDE();
    NodeDE tail = null;
    while (temp != null) {
        if (temp.getData().getAgeDE() != ageDE) {
            NodeDE newNodeDE = new NodeDE(temp.getData());
            if (listDE1.getHeadDE() == null) {
                listDE1.setHeadDE(newNodeDE);
            } else {
                tail.setNext(newNodeDE);
                newNodeDE.setPrev(tail);
            }
            tail = newNodeDE;
        }
        temp = temp.getNext();
    }
    headDE = listDE1.getHeadDE();
    while (headDE.getPrev() != null) {
        headDE = headDE.getPrev();
    }
}
//5
public float averageAgeDE() {
    if (headDE != null) {
        NodeDE temp = headDE;
        int count = 0;
        int agesDE = 0;
        while (temp.getNext() != null) {
            count++;
            agesDE = agesDE + temp.getData().getAgeDE();
            temp = temp.getNext();
        }
        count++;
        agesDE = agesDE + temp.getData().getAgeDE();
        float averageDE = (float) agesDE / count;
        NodeDE current = temp.getPrev();
        while (current != null) {
            count++;
            agesDE = agesDE + current.getData().getAgeDE();
            current = current.getPrev();
        }
        return (float) agesDE / count;
    } else {
        return (int) 0;
    }
}
//7
public void gainPositionKidDE(String identification, int gain) {
    NodeDE temp = headDE;
    int sum = 0;
    ListDE listDE = new ListDE();
    if (headDE != null) {
        while (temp != null && !temp.getData().getIdentificationDE().equals(identification)) {
            listDE.addDE(temp.getData());
            temp = temp.getNext();
        }
        if (temp != null) {
            listDE.addDE(temp.getData());
        }
    }
    sum = gain - getPosByIdDE(identification);
    listDE.addInposDE(getKidByidDE(identification), sum);
    NodeDE act = listDE.getHeadDE();
    while (act != null) {
        addDE(act.getData());
        act = act.getNext();
    }
}
//8
public void LosePositionPetDE(String identification, int lose) {
    NodeDE temp = headDE;
    int sum = 0;
    ListDE listDE = new ListDE();
    if (headDE != null) {
        while (temp != null && !temp.getData().getIdentificationDE().equals(identification)) {
            listDE.addDE(temp.getData());
            temp = temp.getNext();
        }
        if (temp != null) {
            listDE.addDE(temp.getData());
        }
    }
    sum = lose + getPosByIdDE(identification);
    listDE.addInposDE(getKidByidDE(identification), sum);
    NodeDE act = listDE.getHeadDE();
    NodeDE prev = null;
    while (act != null) {
        act.setPrev(prev);
        addDE(act.getData());
        prev = act;
        act = act.getNext();
    }
}
//9
public int rangeByAgeDE(int min, int max) {
    NodeDE temp = headDE;
    int count = 0;
    while (temp != null) {
        if (temp.getData().getAgeDE() > min && temp.getData().getAgeDE() < max) {
            count++;
        }
        temp = temp.getNext();
    }
    return count;
}

}
