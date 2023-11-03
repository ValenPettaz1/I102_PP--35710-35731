package linea;

public class Game {
    public static void main(String[] args) throws Exception {
        System.out.println("Dimensiones? ");
        Linea game = new Linea(intPrompt("Base? "), intPrompt("Altura? "), charPrompt());

        System.out.println(game.show());

        while (!game.finished()) {
            game.playRedAt(intPrompt("Rojas (X)? "));
            System.out.println(game.show());

            if (!game.finished()) {
                game.playBlueAt(intPrompt("Azules (O)? "));
                System.out.println(game.show());
            }
        }
        System.out.println("< Ganaron las " + Linea.getLastColorPlayed() + " >");
    }

    private static int intPrompt(String message) {
        System.out.print(message);
        return Integer.parseInt(System.console().readLine());
    }

    private static char charPrompt() {
        System.out.print("Modo A, B o C? (de elegir mal por predeterminado C) ");
        return System.console().readLine().charAt(0);
    }
}