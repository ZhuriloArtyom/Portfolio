import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.APPEND;

public class geneticAlgorithm {
    int GenerationSize;
    int GenomeLength;
    int ReproductionSize;
    int levels;
    int TournamentSize;
    boolean trainingWheels;

    String logName;
    int IterationsWithWheels;

    int IterationsWithoutWheels;
    Float mutationRate;

    public geneticAlgorithm(int generationSize, int genomeLength, int reproductionSize, int levels, int tournamentSize,
                            int iterationsWithWheels, int iterationsWithoutWheels, Float mutationRate, String logName) {
        GenerationSize = generationSize;
        GenomeLength = genomeLength;
        ReproductionSize = reproductionSize;
        this.levels = levels;
        TournamentSize = tournamentSize;
        this.trainingWheels = true;
        IterationsWithWheels = iterationsWithWheels;
        IterationsWithoutWheels = iterationsWithoutWheels;
        this.mutationRate = mutationRate;
        this.logName =logName;
    }

    public List<PlayerGenome> Selection(List<PlayerGenome> population){
        List<PlayerGenome> selected = new ArrayList<>();
        List<PlayerGenome> tournament;
        PlayerGenome temporaryMax = new PlayerGenome(Collections.max(population).genome, levels, trainingWheels);
        selected.add(temporaryMax);
        for (int i = 0; i <ReproductionSize - 1; i++) {
            tournament = pickRandom(population, TournamentSize);
            assert tournament != null;
            temporaryMax = new PlayerGenome(Collections.max(tournament).genome, levels, trainingWheels);
            selected.add(temporaryMax);
        }
        return selected;
    }

    public PlayerGenome mutate(PlayerGenome player){
        Random random = new Random();
        float mutate = random.nextFloat();
        if (mutate < mutationRate){
            player.genome[random.nextInt(this.GenomeLength)] = random.nextInt(32);
        }
        return player;
    }

    public List<PlayerGenome> cross(PlayerGenome player1, PlayerGenome player2){
        Random random =  new Random();
        int breakage = random.nextInt(this.GenomeLength);
        List<PlayerGenome> children = new ArrayList<>();
        int[] genome1 = new int[this.GenomeLength], genome2 = new int[this.GenomeLength];
        for(int i = 0; i < breakage; i++){
            genome1[i] = player1.genome[i];
            genome2[i] = player2.genome[i];
        }
        for(int i = breakage; i < this.GenomeLength; i++){
            genome1[i] = player2.genome[i];
            genome2[i] = player1.genome[i];
        }
        children.add(new PlayerGenome(genome1, levels, this.trainingWheels));
        children.add(new PlayerGenome(genome2, levels, this.trainingWheels));

        return children;
    }

    public List<PlayerGenome> wheelsOff(List<PlayerGenome> population){
        this.trainingWheels = false;
        List<PlayerGenome> new_Population = new ArrayList<>();
        for (PlayerGenome playerGenome : population) {
            new_Population.add(new PlayerGenome(playerGenome.genome, levels, false));
        }
        return new_Population;
    }

    private List<PlayerGenome> pickRandom(List<PlayerGenome> list, int n){
        Random random= new Random();
        if (list.size()<n)
            return null;
        for(int i = list.size() - 1; i > list.size() - n; i--)
            Collections.swap(list, i, random.nextInt(i+1));
        return list.subList(list.size()-n, list.size());
    }

    public List<PlayerGenome> createGeneration(List<PlayerGenome> population){
        int currentGenerationSize = population.size();
        List<PlayerGenome> generation = new ArrayList<>(population);
        while (currentGenerationSize < GenerationSize){
            List<PlayerGenome> parents = pickRandom(population, 2);
            assert parents != null;
            List<PlayerGenome> children =cross(parents.get(0),parents.get(1));
            children.set(0, mutate(children.get(0)));
            children.set(1, mutate(children.get(1)));
            currentGenerationSize += 2;
            generation.addAll(children);
        }
        return generation;
    }
    public PlayerGenome optimise() throws FileNotFoundException {
        createFiles(logName);
        PrintWriter writer = new PrintWriter("logs/"+logName + "_best.txt");
        writer.print("");
        writer.close();
        writer = new PrintWriter("logs/"+logName + "_average.txt");
        writer.print("");
        writer.close();

        int bestScore = 0;
        int[] bestScoreGenome = new int[0];
        List<PlayerGenome> population = NewPopulation();
        PlayerGenome bestGenome =  population.get(0);
        for (int i = 0; i < IterationsWithWheels + IterationsWithoutWheels; i++){
            if(i == IterationsWithWheels){
                population = wheelsOff(population);
            }
            List<PlayerGenome> selected = Selection(population);
            population = createGeneration(selected);
            if (Collections.max(population).score > bestScore && i >= IterationsWithWheels){
                bestScore = Collections.max(population).score;
                bestScoreGenome = Collections.max(population).genome;
            }
            bestGenome = Collections.max(population);

            writingToFile("logs/"+logName + "_best.txt", bestGenome.score);

            writingToFile("logs/"+logName + "_average.txt", AverageScore(population));

        }

        writingToFile("logs/"+logName + "_best.txt", bestGenome.genome);
        writingToFile("logs/"+logName + "_best.txt", bestScore);
        writingToFile("logs/"+logName + "_best.txt", bestScoreGenome);
        return bestGenome;
    }
    public List<PlayerGenome> NewPopulation(){
        List<PlayerGenome> population = new ArrayList<>();
        for (int i= 0; i< GenerationSize;i++){
            population.add(new PlayerGenome(levels, GenomeLength, trainingWheels));
        }
        return population;
    }

    public int AverageScore(List<PlayerGenome> population){
        int sum = 0;
        for (PlayerGenome playerGenome : population) {
            sum += playerGenome.score;
        }
        return sum/population.size();
    }

    public static void writingToFile(String address, int info) {
        try {
            String newLine = System.getProperty("line.separator");
            FileWriter myWriter = new FileWriter(address, true);
            myWriter.write(Integer.toString(info) + newLine);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void writingToFile(String address, int[] info) {
        try {
            String newLine = System.getProperty("line.separator");
            FileWriter myWriter = new FileWriter(address, true);
            for (int gene : info) {
                myWriter.write(gene + " ");
            }
            myWriter.write(newLine);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void createFiles(String name){
        try {
            File myObj = new File("logs/" + name + "_best.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
            myObj = new File( "logs/" + name + "_average.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}



