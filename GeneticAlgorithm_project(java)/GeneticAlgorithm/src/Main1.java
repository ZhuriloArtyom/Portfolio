import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
public class Main1 {
    public static void main(String[] args) throws FileNotFoundException {
        geneticAlgorithm gene1 = new geneticAlgorithm(50,
                32, 5, 10,
                10, 0, 400, 5f, "noWheels");
        geneticAlgorithm gene2 = new geneticAlgorithm(50,
                32, 5, 10,
                10, 200, 200, 5f, "Wheels");
        geneticAlgorithm gene3 = new geneticAlgorithm(50,
                32, 5, 10,
                10, 0, 400, 8f, "noWheelsHighMutation");
        geneticAlgorithm gene4 = new geneticAlgorithm(50,
                32, 5, 10,
                10, 200, 200, 8f, "WheelsHighMutation");
        geneticAlgorithm gene5 = new geneticAlgorithm(50,
                32, 5, 10,
                10, 0, 400, 2f, "noWheelsLowMutation");
        geneticAlgorithm gene6 = new geneticAlgorithm(50,
                32, 5, 10,
                10, 200, 200, 2f, "WheelsLowMutation");
        geneticAlgorithm gene7 = new geneticAlgorithm(50,
                64, 5, 10,
                10, 200, 200, 5f, "WheelsDoubleGenLength");
        geneticAlgorithm gene8 = new geneticAlgorithm(50,
                64, 5, 10,
                10, 0, 400, 5f, "noWheelsDoubleGenLength");
        geneticAlgorithm gene9 = new geneticAlgorithm(50,
                124, 5, 10,
                10, 200, 200, 5f, "WheelsQuadrupleGenLength");
        geneticAlgorithm gene10 = new geneticAlgorithm(50,
                124, 5, 10,
                10, 0, 400, 5f, "noWheelsQuadrupleGenLength");
        geneticAlgorithm gene11 = new geneticAlgorithm(50,
                16, 5, 10,
                10, 200, 200, 5f, "WheelsHalfGenLength");
        geneticAlgorithm gene12 = new geneticAlgorithm(50,
                16, 5, 10,
                10, 0, 400, 5f, "noWheelsHalfGenLength");
        System.out.println(gene1.optimise());
        System.out.println(gene2.optimise());
        System.out.println(gene3.optimise());
        System.out.println(gene4.optimise());
        System.out.println(gene5.optimise());
        System.out.println(gene6.optimise());
        System.out.println(gene7.optimise());
        System.out.println(gene8.optimise());
        System.out.println(gene9.optimise());
        System.out.println(gene10.optimise());
        System.out.println(gene11.optimise());
        System.out.println(gene12.optimise());
    }


}




