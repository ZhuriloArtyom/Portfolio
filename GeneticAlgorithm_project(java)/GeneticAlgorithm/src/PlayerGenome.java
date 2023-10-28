import java.util.Objects;
import java.util.Random;

public class PlayerGenome implements Comparable<PlayerGenome>{
    int genomeLength;
    int[] genome;
    int score;


    public PlayerGenome(int levels, int genomeLength, boolean trainingWheels) {
        this.genomeLength =genomeLength;
        this.genome = RandomGenome();
        this.score = test(this.genome, levels, trainingWheels);
    }

    public PlayerGenome(int[] genome, int levels, boolean trainingWheels) {
        this.genome = genome;
        genomeLength = genome.length;
        this.score = test(this.genome, levels, trainingWheels);
    }
    private int[] RandomGenome(){
        int[] genome = new int[this.genomeLength];
        Random random = new Random();
        for(int i = 0; i < this.genomeLength; i++){
            genome[i] = random.nextInt(32);
        }
        return genome;
    }
    static int test(int[] genome, int levels, boolean trainingWheels){
        int score = 0;
        for (int i = 1; i <= levels;i++){
            score += play(genome, GenerateRoom(i), trainingWheels);
        }
        return score;
    }
    static int play(int[] genome, room room, boolean trainingWheels){
        int timer = 10000;
        int active_gene = 0;
        int player_x = room.start_x;
        int player_y = 0;
        int score = 0;
        int win_weight = 15;
        int treasure_weight = 10;
        int kill_weight= 10;
        int death_penalty = 20;
        while(timer!=0){
            timer--;
            while (active_gene  >= genome.length){ active_gene = active_gene - genome.length;}
            int gene_action = genome[active_gene];
            switch (gene_action){
                //Look UP
                case 0,1,2 -> {
                    if(player_y + gene_action + 1 >= room.height){
                        active_gene++;
                        continue;
                    }
                    switch (room.map[player_x][player_y + gene_action + 1]) {
                        case "E" -> active_gene += 2;
                        case "T" -> active_gene += 3;
                        case "D" -> active_gene += 4;
                        default -> active_gene++;
                    }
                }
                //Look DOWN
                case 3,4,5 -> {
                    if(player_y - (gene_action % 3) - 1 < 0){
                        active_gene++;
                        continue;
                    }
                    switch (room.map[player_x][player_y - (gene_action % 3) - 1]) {
                        case "E" -> active_gene += 2;
                        case "T" -> active_gene += 3;
                        case "D" -> active_gene += 4;
                        default -> active_gene++;
                    }
                }
                //Look RIGHT
                case 6,7,8-> {
                    if(player_x + (gene_action % 3) + 1 >= room.width){
                        active_gene++;
                        continue;
                    }
                    switch (room.map[player_x + (gene_action % 3) + 1][player_y]) {
                        case "E" -> active_gene += 2;
                        case "T" -> active_gene += 3;
                        case "D" -> active_gene += 4;
                        default -> active_gene++;
                    }
                }
                //Look LEFT
                case 9,10,11-> {
                    if(player_x - (gene_action % 3) - 1 < 0){
                        active_gene++;
                        continue;
                    }
                    switch (room.map[player_x - (gene_action % 3) - 1][player_y]) {
                        case "E" -> active_gene += 2;
                        case "T" -> active_gene += 3;
                        case "D" -> active_gene += 4;
                        default -> active_gene++;
                    }
                }
                //Move UP
                case 12->{
                    if(player_y + 1 >= room.height){
                        active_gene++;
                        continue;
                    }
                    active_gene++;
                    switch (room.map[player_x][player_y + 1]) {
                        case "E" -> {
                            return score - death_penalty;
                        }
                        case "T" -> {
                            score += treasure_weight;
                            room.map[player_x][player_y + 1] = "";
                        }
                        case "D" -> {
                            return score + win_weight;
                        }
                        default -> player_y++;
                    }
                }
                //Move DOWN
                case 13->{
                    if(player_y - 1 < 0){
                        active_gene++;
                        continue;
                    }
                    active_gene++;
                    switch (room.map[player_x][player_y - 1]) {
                        case "E" -> {
                            return score - death_penalty;
                        }
                        case "T" -> {
                            score += treasure_weight;
                            room.map[player_x][player_y - 1] = "";
                        }
                        case "D" -> {
                            return score + win_weight;
                        }
                        default -> player_y--;
                    }
                }
                //Move RIGHT
                case 14->{
                    if(player_x + 1 >= room.width){
                        active_gene++;
                        continue;
                    }
                    active_gene++;
                    switch (room.map[player_x + 1][player_y]) {
                        case "E" -> {
                            return score - death_penalty;
                        }
                        case "T" -> {
                            score += treasure_weight;
                            room.map[player_x + 1][player_y] = "";
                        }
                        case "D" -> {
                            return score + win_weight;
                        }
                        default -> player_x++;
                    }
                }
                //Move LEFT
                case 15->{
                    if(player_x - 1 < 0){
                        active_gene++;
                        continue;
                    }
                    active_gene++;
                    switch (room.map[player_x - 1][player_y]) {
                        case "E" -> {
                            return score - death_penalty;
                        }
                        case "T" -> {
                            score += treasure_weight;
                            room.map[player_x - 1][player_y] = "";
                        }
                        case "D" -> {
                            return score + win_weight;
                        }
                        default -> player_x--;
                    }
                }
                //Attack UP
                case 16->{
                    if(player_y + 1 >= room.height){
                        active_gene++;
                        continue;
                    }
                    if (Objects.equals(room.map[player_x][player_y + 1], "E")){
                        room.map[player_x][player_y + 1] = "";
                        score += kill_weight;
                    }
                    active_gene++;
                }
                //Attack DOWN
                case 17->{
                    if(player_y - 1 < 0){
                        active_gene++;
                        continue;
                    }
                    if (Objects.equals(room.map[player_x][player_y - 1], "E")){
                        room.map[player_x][player_y - 1] = "";
                        score += kill_weight;
                    }
                    active_gene++;
                }
                //Attack RIGHT
                case 18->{
                    if(player_x + 1 >= room.width){
                        active_gene++;
                        continue;
                    }
                    if (Objects.equals(room.map[player_x + 1][player_y], "E")){
                        room.map[player_x + 1][player_y] = "";
                        score += kill_weight;
                    }
                    active_gene++;
                }
                //Attack LEFT
                case 19->{
                    if(player_x - 1 < 0){
                        active_gene++;
                        continue;
                    }
                    if (Objects.equals(room.map[player_x - 1][player_y], "E")){
                        room.map[player_x - 1][player_y] = "";
                        score += kill_weight;
                    }
                    active_gene++;
                }
                default ->  active_gene += gene_action;
            }
        }
        if(trainingWheels) score += player_y * 3;
        return score;
    }

    public static room GenerateRoom(int level) {
        Random rand = new Random();
        int width = 5 * (level / 3 + 1) + rand.nextInt(3);
        int height = 5 * (level / 3 + 1) + rand.nextInt(3);
        int start_x = rand.nextInt(width);
        String[][] map = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = "";
            }
        }
        map[rand.nextInt(width)][height - 1] = "D";
        map[start_x][0] = "S";
        for (int i = 0; i < level / 2; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            if (map[x][y].isEmpty()) {
                map[x][y] = "E";
            } else i--;
        }
        for (int i = 0; i < (level / 2 + 1); i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            if (map[x][y].isEmpty()) {
                map[x][y] = "T";
            } else i--;
        }
        return new room(height, width, start_x, map);
    }

    @Override
    public int compareTo(PlayerGenome o) {
        if(this.score == o.score) return 0;
        return(this.score - o.score);
    }
}
