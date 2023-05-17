package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.ListSEException.ListDEException;
import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ReportPetsLocationGenderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListDE {
    private NodeDE headDE;
    private int size;
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

    public void getPetByidDE(String identification) {
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
        Pet pet = new Pet(temp.getData().getName(), temp.getData().getBred(),
                temp.getData().getIdentificationDE(), temp.getData().getAgeDE(),
                temp.getData().getGenderDE(), temp.getData().getOwner(), temp.getData().getLocation());
    }

    //(temp.getData().getIdentificationDE(), temp.getData().getName(),temp.getAgeDE())
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
    public void addbypositionDE(Pet pet, int position) {
        NodeDE newNodoDE = new NodeDE(pet);
        if (position == 0) {
            newNodoDE.setNext(headDE);
            headDE = newNodoDE;
        } else {
            NodeDE act = headDE;
            for (int i = 1; i < position - 1; i++) {
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

    public void addToEnd(Pet pet) {
        if (headDE != null) {
            NodeDE newNodeDE = new NodeDE(pet);
            NodeDE temp = headDE;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNodeDE);
            newNodeDE.setPrev(temp);
        } else {
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
    public void deletePetByAgeDE(byte ageDE) {
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
    public String reportByAgeDE() {
        int quan1 = 0;
        int quan2 = 0;
        int quan3 = 0;
        int quan4 = 0;
        int quan5 = 0;
        NodeDE temp = this.headDE;
        if (this.headDE != null) {
            while (temp != null) {
                if (temp.getData().getAgeDE() >= 0 && temp.getData().getAgeDE() <= 3) {
                    quan1++;
                } else if (temp.getData().getAgeDE() > 2 && temp.getData().getAgeDE() <= 6) {
                    quan2++;
                } else if (temp.getData().getAgeDE() > 6 && temp.getData().getAgeDE() <= 9) {
                    quan3++;
                } else if (temp.getData().getAgeDE() > 9 && temp.getData().getAgeDE() <= 12) {
                    quan4++;
                } else if (temp.getData().getAgeDE() > 12 && temp.getData().getAgeDE() <= 15) {
                    quan5++;
                }
                temp = temp.getNext();
            }
        }
        return "mascotas entre 0 y 3 años :" + quan1 + "mascotas entre 4 y 6 años " + quan2 +
                "mascotas entre 7 y 9 años " + quan3 + "mascotas entre 10 y 12 años " + quan4 +
                "mascotas entre 13 y 15 años" + quan5;
    }


    //10
    public void addToFinalNameCharDE(String letter) throws ListDEException {
        if (headDE != null) {
            ListDE listCp = new ListDE();
            NodeDE temp = headDE;
            if (temp.getData().getName().startsWith(letter)) {
                listCp.addDE(temp.getData());
                temp = temp.getNext();
            } else {
                listCp.addDE(temp.getData());
                temp = temp.getNext();
            }
            headDE = listCp.getHeadDE();
        }
    }

    public void changeExtremesDE() {
        if (this.headDE != null && this.headDE.getNext() != null) {
            NodeDE temp = this.headDE;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }

            Pet copy = this.headDE.getData();
            this.headDE.setData(temp.getData());
            temp.setData(copy);
        }
    }


    public int getCountKidsByLocationCode(String code) {
        int count = 0;
        if (this.headDE != null) {
            NodeDE temp = this.headDE;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int verifyId(PetDTO pet) {
        NodeDE temp = this.headDE;
        Boolean found = false;
        while (temp != null) {
            if (temp.getData().getIdentificationDE().equals(pet.getIdentificationDE())) {
                found = true;
                break;
            }
            temp = temp.getNext();
        }
        return found ? 1 : 0;
    }


    //metodo para añadir nuevo nodo y nuevo niño en un posicion
    public void addInpos(Pet pet, int pos) {
        NodeDE temp = headDE;
        for (int i = 0; i < pos; i++) {
            temp = temp.getNext();
        }
        NodeDE newNode = new NodeDE(pet);
        temp.setNext(new NodeDE(pet));
    }


    //metodo para eliminar niños por id
    public void deleteByidentification(String identification) {
        NodeDE currentNodeDE = headDE;
        NodeDE prevNode = null;

        while (currentNodeDE != null && currentNodeDE.getData().getIdentificationDE() != identification) {
            prevNode = currentNodeDE;
            currentNodeDE = currentNodeDE.getNext();
        }
        if (currentNodeDE != null) {
            if (prevNode == null) {
                headDE = currentNodeDE.getNext();
            } else {
                prevNode.setNext(currentNodeDE.getNext());
            }
        }
    }

    //adelantar en posicion
    public void advanceInpos(Pet pet, int pos) {
        NodeDE temp = headDE;
        for (int i = 0; i < pos; i++) {
            temp = temp.getNext();
        }
        NodeDE newNode = new NodeDE(pet);
        temp.setNext(newNode);
    }

    //añadir por posicion
    public void addbyposition(Pet pet, int position) {
        NodeDE nuevoNodoDE = new NodeDE(pet);
        if (position == 0) {
            nuevoNodoDE.setNext(headDE);
            headDE = nuevoNodoDE;
        } else {
            NodeDE act = headDE;
            for (int i = 1; i < position - 1; i++) {
                act = act.getNext();
            }
            nuevoNodoDE.setNext(act.getNext());
            act.setNext(nuevoNodoDE);
        }
    }

    //metodo para obtener la lista de ciudad y ademas se sabra cuantos niños y niñas hay por separado
    public void getReportKidsByLocationGendersByAge(byte age, ReportPetsLocationGenderDTO report) {
        if (headDE != null) {
            NodeDE temp = this.headDE;
            while (temp != null) {
                if (temp.getData().getAgeDE() > age) {
                    report.updateQuantity(
                            temp.getData().getLocation().getName(),
                            temp.getData().getGenderDE());
                }
                temp = temp.getNext();
            }


        }
    }


    private int getPosByIdentification(String identification) {
        return 0;
    }
    //get Kid By id

    public Pet getKidByidDE(String id) {
        NodeDE temp = headDE;
        if (headDE != null) {
            while (temp != null) {
                temp.getNext();
                while (!temp.getData().getIdentificationDE().equals(id)) {
                    temp.getNext();
                }
                temp.getData();

            }
        }
        Pet pet = new Pet(temp.getData().getName(), temp.getData().getBred(),
                temp.getData().getIdentificationDE(), temp.getData().getAgeDE(),
                temp.getData().getGenderDE(), temp.getData().getOwner(), temp.getData().getLocation());
        return pet;

    }


    public int getCountKidsBylocationAndGenderM(String code) {
        int count = 0;
        int countm = 0;
        int countf = 0;

        if (this.headDE != null) {
            NodeDE temp = this.headDE;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                    if (temp.getData().getGenderDE() == 'M') {
                        countm++;
                    }
                }
            }
            temp = temp.getNext();
        }
        return countm;
    }

    public int getCountKidsBylocationAndGenderF(String code) {
        int count = 0;
        int countm = 0;
        int countf = 0;

        if (this.headDE != null) {
            NodeDE temp = this.headDE;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
            }
            temp = temp.getNext();
        }
        return countf;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodeDE temp = this.headDE;
        sb.append("[");
        while (temp != null) {
            sb.append(temp.getData().toString());
            temp = temp.getNext();
            if (temp != null) {
                sb.append(",");
            }
        }
        sb.append("[");
        return sb.toString();
    }
    /*
    preguntamos a cabeza si tiene datos
    si tiene datos ,
    si el nodo que necesitamos es la cabeza le decimos a la cabeza que tome el nodo siguiente (node.next) y
    donde esta la cabeza que tome a previous igual a nulo



    le decimos a temp que recorra toda la lista y que se pare en el nodo con el id pedido
    le decimos al nodo anteriror  (nos.previous) que tome al nodo sieguiente (node.next) de el nodo con el id pedido
    y a el nodo siguiente que tome al nodo anterior


    si necesitamos el ultimo nodo le dicemos al temp que tome el nodo anterior(node.previous) y que tome a next como
    nulo


     */

    public void deletePetByIdNode(String iddentification) {
        if (this.headDE != null) {

            if (this.headDE.getData().getIdentificationDE().equals(iddentification)) {
                headDE = headDE.getNext();
                if (headDE != null) {
                    headDE.setPrev(null);
                }
            } else {
                NodeDE temp = headDE;
                while (temp != null) {

                    if (temp.getData().getIdentificationDE().equals(iddentification)) {
                        temp.getPrev().setNext(temp.getNext());
                        if (temp.getNext() != null) {
                            temp.getNext().setPrev(temp.getPrev());
                        }
                    }
                    temp = temp.getNext();

                }

            }
        }
    }
}

