package github.io.lazyomlette.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static github.io.lazyomlette.Main.viewport;

public class WorldPhysics {
    private static World world;
    private Box2DDebugRenderer debugRenderer;
    private PlayerPhysics playerPhysics;


    public WorldPhysics() {

        // Initialize Box2D world with gravity
        this.playerPhysics = playerPhysics;
        world = new World (new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        /*world.setContactListener(new ContactListener(){
            @Override
            public void beginContact(Contact contact) {
                Fixture a = contact.getFixtureA();
                Fixture b = contact.getFixtureB();

                if (isPlayerGroundSensor(a,b)) {
                    playerPhysics.setGrounded(true);
                }
            }


            @Override
            public void endContact(Contact contact) {

              Fixture a = contact.getFixtureA();
              Fixture b = contact.getFixtureB();

                if (isPlayerGroundSensor(a,b)) {
                    playerPhysics.setGrounded(false);
                }

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}

            private boolean isPlayerGroundSensor(Fixture a, Fixture b) {
             return (a == playerPhysics.getGroundSensor() || b == playerPhysics.getGroundSensor()); 

            }




            */
        }

    public static World getWorld() {
          return world;

    }

    public void update(float deltaTime){
        // Update physics simulation
        world.step(deltaTime, 6,2);
    }

    public void renderDebug(){
        // Render debug info
        debugRenderer.render(world, viewport.getCamera().combined);

    }

    public void dispose(){

        world.dispose();
        debugRenderer.dispose();
    }

    //ect...
}

