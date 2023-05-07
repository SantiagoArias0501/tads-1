package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import lombok.Data;

@Data
public class ListSE {
    private Node head;
    private int size;

    public void add(Kid kid) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {

                temp = temp.getNext();
            }
            // para en el ultimo
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
        size++;
    }


    //get in pos by id
    public int getPosById(String id) {
        Node temp = head;
        int acum = 0;
        if (head != null) {
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                acum++;
                temp.getNext();
                return acum;
            }
        }
        return acum;
    }
    //1 invertir lista
    public void invert() {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                listCp.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    // 2 Adicionar al inicio

    public void orderBoysToStart() {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {

                    listCp.addToStart(temp.getData());
                } else {
                    listCp.add(temp.getData());
                }
                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
        size++;

    }

        // 3 intercalar niño y niña
    public void IntercaleBoyandGirl(){
        ListSE listM = new ListSE();
        ListSE listF = new ListSE();
        Node temp = head;
        while (temp != null){
            if (temp.getData().getGender()== 'M'){}
            listM.add(temp.getData());
        }
        if (temp.getData().getGender()== 'F'){
            listF.add(temp.getData());
        }
        temp = temp.getNext();


    }
    // 4 eliminar por edad dada
    public void DeleteByAge(Byte age) {
        Node temp = head;

        ListSE listSE1 = new ListSE();
        //recorrer la lista original y crear la lista copia

        while (temp != null) {
            if (temp.getData().getAge() != age) {
                listSE1.addToStart(temp.getData());
                temp.getNext();

            }
        }
        this.head = listSE1.getHead();
    }

    //5 obtener el promedio de edad de los niños de la lista
    public float averageAge(){
        if (head != null){
            Node temp = head;
            int count = 0;
            int ages =0;
            while (temp.getNext()!=null){
                count++;
                ages = ages + temp.getData().getAge();
                temp = temp.getNext();
            }
            return (float) ages/count;
        }else{
            return (int) 0;
        }
    }
    // 7 metodo para definirle que adelante un numero dado de posiciones
    public void gainPositionKid(String id,int gain){
        Node temp= head;
        gain=0;
        int sum=0;
        ListSE listSECp =new ListSE();
        if (head !=null){
            while (temp !=null && !temp.getData().getIdentification().equals(id)){
                listSECp.add(temp.getData());
            }
            temp.getNext();
        }
        sum=gain-getPosById(id);
        listSECp.addInpos(getKidByid(id),sum);
    }

    //8 perder posiciones
    public  void LosePositionKid(String id,int lose){
        Node temp = head;
        lose = 0;
        int sum = 0;
        ListSE listSE1 = new ListSE();
        if (head != null){
            while (temp != null && !temp.getData().getIdentification().equals(id)){
                listSE1.add(temp.getData());
            }
        }
        sum = lose + getPosById(id);
        listSE1.addInpos(getKidByid(id), sum);

    }
    //9
    public String reportByAge() {
        int quan1 = 0;
        int quan2 = 0;
        int quan3 = 0;
        int quan4 = 0;
        int quan5 = 0;
        Node temp = this.head;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getAge() >= 0 && temp.getData().getAge() <= 3) {
                    quan1++;
                } else if (temp.getData().getAge() > 2 && temp.getData().getAge() <= 6) {
                    quan2++;
                } else if (temp.getData().getAge() > 6 && temp.getData().getAge() <= 9) {
                    quan3++;
                } else if (temp.getData().getAge() > 9 && temp.getData().getAge() <= 12) {
                    quan4++;
                } else if (temp.getData().getAge() > 12 && temp.getData().getAge() <= 15) {
                    quan5++;
                }
                temp = temp.getNext();
            }
        }
        return "mascotas entre 0 y 3 años :"+quan1+"mascotas entre 4 y 6 años "+quan2+
                "mascotas entre 7 y 9 años "+quan3+"mascotas entre 10 y 12 años "+quan4+
                "mascotas entre 13 y 15 años"+quan5;
    }

    // 10
    public void addToFinalNameChar(String letter) {
        ListSE listSECp = new ListSE();
        Node temp = head;
        if (this.head != null) {
            while (temp != null) {
                if (temp.getData().getName().startsWith(letter) != temp.getData().getName().startsWith(letter)) {
                    listSECp.addToStart(temp.getData());
                } else {
                    listSECp.add(temp.getData());
                }
                temp = temp.getNext();
            }
        }
        this.head = listSECp.getHead();
    }
    public void changeExtremes(){
        if (this.head !=null && this.head.getNext()!=null)
        {
            Node temp = this.head;
            while(temp.getNext()!=null)
            {
                temp = temp.getNext();
        }
            //remp esta de ultimo
            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
    }



    public int getCountKidsByLocationCode(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int verifyId(KidDTO kid){
        Node temp = this.head;
        Boolean found = false;
        while (temp !=null){
            if(temp.getData().getIdentification().equals(kid.getIdentification())){
                found = true;
                break;
            }
            temp = temp.getNext();
        }
        return found ?1 :0;
    }


    //metodo para añadir nuevo nodo y nuevo niño en un posicion
    public void addInpos(Kid kid, int pos) {
        Node temp = head;
        for (int i = 0; i < pos; i++) {
            temp = temp.getNext();
        }
        Node newNode = new Node(kid);
        temp.setNext(newNode);
    }


    //metodo para eliminar niños por id
    public void deleteByidentification(String identification){
        Node currentNode = head;
        Node prevNode= null;

        while (currentNode != null && currentNode.getData().getIdentification()!= identification){
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        if (currentNode != null){
            if (prevNode == null){
                head = currentNode.getNext();
            }else{
                prevNode.setNext(currentNode.getNext());
            }
        }
    }

    //adelantar en posicion
    public void advanceInpos(Kid kid, int pos) {
        Node temp = head;
        for (int i = 0; i < pos; i++) {
            temp = temp.getNext();
        }
        Node newNode = new Node(kid);
        temp.setNext(newNode);
    }
    //añadir por posicion
    public void addbyposition(Kid kid,int position){
        Node nuevoNodo = new Node(kid);
        if (position ==0){
            nuevoNodo.setNext(head);
            head = nuevoNodo;
        }else {
            Node actual = head;
            for (int i = 1; i < position - 1;i++){
                actual = actual.getNext();
            }
            nuevoNodo.setNext(actual.getNext());
            actual.setNext(nuevoNodo);
        }
    }
    //metodo para obtener la lista de ciudad y ademas se sabra cuantos niños y niñas hay por separado
    public void getReportKidsByLocationGendersByAge(byte age, ReportKidsLocationGenderDTO report) {
        if (head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getAge() > age) {
                    report.updateQuantity(
                            temp.getData().getLocation().getName(),
                            temp.getData().getGender());
                }
                temp = temp.getNext();
            }


        }
    }


    private int getPosByIdentification(String identification){return 0;}
    //get Kid By id

    public Kid getKidByid(String id) {
        Node temp = head;
        if (head != null) {
            while (temp != null) {
                temp.getNext();
                while (!temp.getData().getIdentification().equals(id)) {
                    temp.getNext();
                }
                temp.getData();

            }
        }
        Kid kid =new Kid(temp.getData().getIdentification(),temp.getData().getName(),temp.getData().getAge());
        return kid;

    }



}





