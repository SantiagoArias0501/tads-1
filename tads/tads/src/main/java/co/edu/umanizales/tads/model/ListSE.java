package co.edu.umanizales.tads.model;

import ch.qos.logback.core.util.DelayStrategy;
import co.edu.umanizales.tads.ListSEException.ListSEException;
import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import lombok.Data;

@Data
public class ListSE {
    private Node head;
    private int size;

    public void add(Kid kid) throws ListSEException {
        if (kid == null) {
            throw new ListSEException("400", "No se puede agregar un niño nulo a la lista");
        }
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                if (temp.getData().getIdentification().equals(kid.getIdentification())) {
                    throw new ListSEException("400", "Ya existe un niño con ese codigo");
                }
                temp = temp.getNext();


            }
            if (temp.getData().getIdentification().equals(kid.getIdentification())) {
                throw new ListSEException("400", "Ya existe un niño con ese codigo");
            }
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
                acum = acum + 1;
                temp = temp.getNext();

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

    public void orderBoysToStart() throws ListSEException {
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
    public void intercaleboyandgirl() throws ListSEException {
        ListSE listSE1 = new ListSE();
        int sum = 0;
        Node temp = head;
        if (head != null) {
        } else {
            while (temp != null) {
                if (temp.getData().getGender() == 'F') {
                    listSE1.addToStart(temp.getData());
                }
                temp = temp.getNext();
            }
            temp = head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listSE1.addInpos(temp.getData(), sum);
                    temp = temp.getNext();
                    sum = sum + 2;
                } else {
                    temp = temp.getNext();
                }
            }
            this.head = listSE1.getHead();
        }
    }

    // 4 eliminar por edad dada
    public void deleteByAge(Byte age) {
        Node temp = head;

        ListSE listSE1 = new ListSE();


        while (temp != null) {
            if (temp.getData().getAge() != age) {
                listSE1.addToStart(temp.getData());
            }
            temp = temp.getNext();

        }
        this.head = listSE1.getHead();
        size--;
    }


    //5 obtener el promedio de edad de los niños de la lista
    public float averageAge() {
        if (head != null) {
            Node temp = head;
            int count = 0;
            int ages = 0;
            while (temp.getNext() != null) {
                count++;
                ages = ages + temp.getData().getAge();
                temp = temp.getNext();
            }
            return (float) ages / count;
        } else {
            return (int) 0;
        }
    }

    // 7 metodo para definirle que adelante un numero dado de posiciones
    public void gainPositionKid(String id, int gain) throws ListSEException {
            Node temp = head;
            int sum;
            ListSE listSECp = new ListSE();
            if (head != null) {
                while (temp != null) {
                    if (!temp.getData().getIdentification().equals(id)) {
                        listSECp.add(temp.getData());
                        temp = temp.getNext();
                    } else {

                        temp = temp.getNext();
                    }
                }
            }
            sum =getPosById(id)-gain;
            listSECp.addInpos(getKidByid(id), sum);
            this.head = listSECp.getHead();
        }







    //8 perder posiciones
    public  void losePositionKid(String id,int lose) throws ListSEException {
        Node temp = head;
        int sum = 0;
        ListSE listSE2 = new ListSE();
        if (head != null){
            while (temp != null) {
                if (!temp.getData().getIdentification().equals(id)) {
                    listSE2.add(temp.getData());
                    temp = temp.getNext();

                } else {
                    temp = temp.getNext();
                }
            }
        }
        sum = lose+getPosById(id);
        listSE2.addInpos(getKidByid(id), sum);
        this.head=listSE2.getHead();
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
        return "niños entre 0 y 3 años :" +quan1+
                "niños entre 4 y 6 años "+quan2+
                "niños entre 7 y 9 años "+quan3+
                "niños entre 10 y 12 años "+quan4+
                "niños entre 13 y 15 años"+quan5;
    }

    // 10
    public void addToFinalNameChar(String letter) throws ListSEException {
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

    public int verifyId(Kid kid){
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

    /*
    si hay datos
     llamos a un temporal para que agregue el kid a la lista
     si head es diferente de null
     si el contador el mayor al la lista agrueguelo al final
     si es menor agregurlo al inicio



     si no hay datos
    */
    public void addInpos(Kid kid,int position)throws ListSEException {
        Node temp = this.head;
        Node newNode= new Node(kid);
        if (this.head != null) {
            if (position > size) {
                add(kid);
            } else if (position < 0) {
                addToStart(kid);
            } else {
                    for (int i = 0;temp.getNext() != null && i < position; i++) {
                        temp = temp.getNext();
                    }
                temp.setNext(newNode);
                }
            size++;
            }
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
                if (!temp.getData().getIdentification().equals(id)) {
                    temp = temp.getNext();
                }else {
                    Kid kid =new Kid(temp.getData().getIdentification(),temp.getData().getName(),
                            temp.getData().getAge(),temp.getData().getGender(), temp.getData().getLocation());
                    return kid;
                }
            }
        }

    return null;
    }



}





