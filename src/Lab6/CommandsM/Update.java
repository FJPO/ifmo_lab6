package Lab6.CommandsM;

import Lab6.Source.LabWork;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, осуществляющий перезапись элемента по его индексу
 */
public class Update extends AddElement {

    private int id;
    private String answer;

    @Override
    public String getName() {
        return "UPDATE";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        int[] index = list.stream().filter(element -> element.getId() == id).mapToInt(list::indexOf).toArray();

        if(index.length != 0) {
            getToAdd().setId(id);
            list.remove(index[0]);
            list.add(index[0], getToAdd());

            answer = "Элемент обновлен";
        }
        else answer = "Такой элемент не найден";

    }

    @Override
    public void collectData(String arg) {
        try{
            id = Integer.parseInt(arg);
        } catch(NumberFormatException e){
            System.out.println("Введите id элемента:");
            collectData(new Scanner(System.in).nextLine());
            return;
        }
        super.collectData(arg);
    }

    @Override
    public void recordData(String arg) {
        try {
            id = Integer.parseInt(String.valueOf(arg.toCharArray(), 0, arg.indexOf(" ")));
            super.recordData(String.valueOf(arg.toCharArray(), arg.indexOf(" ") + 1, arg.length() - arg.indexOf(" ") - 1));
        }catch(Exception e){
            setIfCorrect(false);
        }
    }

    @Override
    public void printAnswer() {
        System.out.println(answer);
    }
}
