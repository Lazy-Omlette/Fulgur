package github.io.lazyomlette.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PlayerPhysics {
    private Body body;
    private boolean isGrounded;
    private Fixture groundSensor;

    public PlayerPhysics(World world){
        createPlayerBody(world);

    }

    private void createPlayerBody(World world) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,2);

        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.5f;

        body.createFixture(fixtureDef);
        shape.dispose();

        PolygonShape groundSensorShape = new PolygonShape();
       // groundSensorShape.setAsBox(0.4f,0.1f, new Vector2(0,-0,6f), 0);

        FixtureDef sensorFixtureDef = new FixtureDef();
        sensorFixtureDef.shape = groundSensorShape;
        sensorFixtureDef.isSensor = true;

        groundSensor = body.createFixture(sensorFixtureDef);
        groundSensorShape.dispose();





    }

    public void jump(){
        if(isGrounded) {
            body.applyLinearImpulse(new Vector2(0,5f), body.getWorldCenter(), true);
            isGrounded = false;
        }
    }

    public void update(){


    }

    public Body getBody() {
        return body;
    }

    public void setGrounded(boolean grounded) {
        isGrounded = grounded;
    }
}
