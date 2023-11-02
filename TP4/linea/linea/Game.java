package linea;

public class Game {
    public static void main( String[] args) throws Exception {
        System.out.println( "Dimensiones? ");
        Linea game = new Linea( prompt( "Base? " ), prompt( "Altura? " ), 'C');

        System.out.println( game.show() );

        while ( !game.finished() ) {
            game.playRedAt( prompt( "Rojas (X)? " ) );
            System.out.println( game.show() );

            if ( !game.finished() ) {
                game.playBlueAt( prompt( "Azules (O)? " ) );
                System.out.println( game.show() );
            }
        }
        System.out.println("< Ganaron las " + Linea.getLastColorPlayed() + " >");
    }

    private static int prompt( String message ) {
        System.out.print( message );
        return Integer.parseInt( System.console().readLine() );
    }
}