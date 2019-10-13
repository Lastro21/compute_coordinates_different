package com.testincrement.testincrement.controllers;

import com.google.gson.Gson;
import com.testincrement.testincrement.global.GlobalSetTime;
import com.testincrement.testincrement.models.ModelCoordinatesXY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static java.lang.Math.pow;

@RestController
public class GetCoordinates {

    private final static Gson gson = new Gson();

    private GlobalSetTime globalSetTime;

    private final static ArrayList<ArrayList> firstWrapper = new ArrayList();
    private final static ArrayList<ArrayList> bigWrapper = new ArrayList();

    private ArrayList arrayDistanse = new ArrayList();

    @RequestMapping(value = "/coordinate_x_y", method = RequestMethod.GET)
    synchronized public void getCoordinate_x(@RequestParam(value = "param") String param) {

        final ModelCoordinatesXY modelCoordinatesXY = gson.fromJson(param, ModelCoordinatesXY.class);

        long currentTime = System.currentTimeMillis();

        if (globalSetTime.getBeginTime() == 0) {
            globalSetTime.setBeginTime(currentTime - 17);
        }
        long paramTime = currentTime - globalSetTime.getBeginTime();

        globalSetTime.setBeginTime(currentTime);

        ArrayList<Integer> predictors = new ArrayList(Arrays.asList(0, 1, 2));

        predictors.set(0, modelCoordinatesXY.getCoordinate_x());
        predictors.set(1, modelCoordinatesXY.getCoordinate_y());
        predictors.set(2, (int) paramTime);


        if (paramTime < 500) {
            System.out.println("Меньше");
            firstWrapper.add(predictors);
        } else {
            System.out.println("Больше");
            ArrayList<String> b = (ArrayList<String>) firstWrapper.clone();
            bigWrapper.add(b);
            firstWrapper.clear();
        }

        System.out.println(firstWrapper.size());
        System.out.println(firstWrapper);
        System.out.println(bigWrapper);
        System.out.println(bigWrapper.size());

//        if (bigWrapper.size() == 5) {
//            for (int i = 0; i < bigWrapper.size(); i++) {
//                System.out.println("Размер элемента " + i + " = " + bigWrapper.get(i).size());
//                for (int i1 = 0; i1 < bigWrapper.get(i).size(); i1++) {
//                    //System.out.println(bigWrapper.get(i).get(i1));
//                    ArrayList rrt = new ArrayList((Collection) bigWrapper.get(i).get(i1));
//                    for (int i2 = 0; i2 < 1; i2++) {
//                        System.out.println("X = " + rrt.get(0));
//                        System.out.println("Y = " + rrt.get(1));
//                        System.out.println("TIME = " + rrt.get(2));
//                    }
//                }
//            }
//        }

        if (bigWrapper.size() == 5) {
            for (int i = 1; i < bigWrapper.size(); i++) {
                // Сразу получаем последний и первый элемент чтобы снять КООРДИНАТЫ
                // для проведения воображаемой линии (ПРЯМОЙ)
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                System.out.println(bigWrapper.get(i).get(0));
                ArrayList firstElement = new ArrayList((Collection) bigWrapper.get(i).get(0));
                System.out.println("Вытаскиваем X начальной точки " + firstElement.get(0));
                System.out.println("Вытаскиваем Y начальной точки " + firstElement.get(1));
                int startPoint_X = (int) firstElement.get(0);
                int startPoint_Y = (int) firstElement.get(1);

                System.out.println(bigWrapper.get(i).get(bigWrapper.get(i).size() - 1));
                ArrayList secondElement = new ArrayList((Collection) bigWrapper.get(i).get(bigWrapper.get(i).size() - 1));
                System.out.println("Вытаскиваем X конечной точки " + secondElement.get(0));
                System.out.println("Вытаскиваем Y конечной точки " + secondElement.get(1));
                int endPoint_X = (int) secondElement.get(0);
                int endPoint_Y = (int) secondElement.get(1);
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

                // Здесь начинаем перебор со второго элемента МАССИВА
                // так как первый почти всегда ГРЯЗНЫЙ (нули содержатся)
                for (int i1 = 0; i1 < bigWrapper.get(i).size(); i1++) {
                    ArrayList innerArray = new ArrayList((Collection) bigWrapper.get(i).get(i1));
                    System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
                    System.out.println("Первый элемент X в этой подцепи = " + startPoint_X);
                    System.out.println("Первый элемент Y в этой подцепи = " + startPoint_Y);
                    System.out.println("Последний элемент X в этой подцепи = " + endPoint_X);
                    System.out.println("Последний элемент Y в этой подцепи = " + endPoint_Y);


                    // Здесь начинаем ГЕОМЕТРИЧЕСКИЕ рассчеты /////////////////////////////////////////////////////////////
                    // Получаем ближайшую точку пересечения с ПРЯМОЙ
                    int originPoint_X = (int) innerArray.get(0);
                    int originPoint_Y = (int) innerArray.get(1);

                    float x1 = startPoint_X, x2 = endPoint_X, x4;
                    float y1 = startPoint_Y, y2 = endPoint_Y, y4;
                    float x3 = originPoint_X;
                    float y3 = originPoint_Y;

                    x4 = (float) (((x2 - x1) * (y2 - y1) * (y3 - y1) + x1 * pow(y2 - y1, 2) + x3 * pow(x2 - x1, 2)) / (pow(y2 - y1, 2) + pow(x2 - x1, 2)));
                    y4 = (y2 - y1) * (x4 - x1) / (x2 - x1) + y1;

                    System.out.println("Точка пересечения по X = " + x4);
                    System.out.println("Точка пересечения по Y = " + y4);

                    // Находим расстояние от нашей точки до точки, котрую мы ВЫЧИСЛИЛИ
                    float distance = (float) Math.hypot(x3 - x4, y3 - y4);
                    System.out.println("Расстояние между оригинальной и точкой на опорном векторе = " + distance);

                    arrayDistanse.add(distance);

                    //   /   Получаем ближайшую точку пересечения с ПРЯМОЙ ////////////////////////////////////////////////


                    System.out.println("X = " + innerArray.get(0));
                    System.out.println("Y = " + innerArray.get(1));
                    System.out.println("TIME = " + innerArray.get(2));
                    System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

                }
            }
        }
        if (!arrayDistanse.isEmpty()) {
            System.out.println(arrayDistanse);
            System.out.println(arrayDistanse.size());
            for (int i = 0; i < arrayDistanse.size(); i++) {
                System.out.println(arrayDistanse.get(i));
            }
        }

    }

    @Autowired
    public void setGlobalSetTime(GlobalSetTime globalSetTime) {
        this.globalSetTime = globalSetTime;
    }
}
