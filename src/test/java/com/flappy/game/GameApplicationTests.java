package com.flappy.game;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameApplicationTests {

	@Test
	void testObjectPoolCreation() {
		GameObjectPool<Bullet> bulletPool = new GameObjectPool<>(10, Bullet.class);
		assertNotNull(bulletPool);
	}

	@Test
	void testGetObjectFromPool() {
		GameObjectPool<Bullet> bulletPool = new GameObjectPool<>(10, Bullet.class);
		Bullet bullet = bulletPool.getObject();
		assertNotNull(bullet);
		assertTrue(bullet.isActive());
	}

	@Test
	void testReleaseObjectToPool() {
		GameObjectPool<Bullet> bulletPool = new GameObjectPool<>(10, Bullet.class);
		Bullet bullet = bulletPool.getObject();
		bullet.setActive(false);
		bulletPool.releaseObject(bullet);
		assertFalse(bullet.isActive());
	}

	@Test
	void testObjectPoolSize() {
		int initialSize = 10;
		GameObjectPool<Bullet> bulletPool = new GameObjectPool<>(initialSize, Bullet.class);
		assertEquals(initialSize, bulletPool.getPoolSize());
	}

	@Test
	void testObjectPoolExpansion() {
		int initialSize = 10;
		int additionalSize = 5;
		GameObjectPool<Bullet> bulletPool = new GameObjectPool<>(initialSize, Bullet.class);
		for (int i = 0; i < additionalSize; i++) {
			bulletPool.getObject();
		}
		assertEquals(initialSize + additionalSize, bulletPool.getPoolSize());
	}

	@Test
	void testBulletInitialization() {
		Bullet bullet = new Bullet();
		bullet.Init(100, 200);
		
		assertTrue(bullet.isActive());
		assertEquals(100, bullet.getX());
		assertEquals(200, bullet.getY());
		assertEquals(Bullet.MAX_RANGE, bullet.getRange());
	}

	@Test
	void testBulletUpdate() {
		Bullet bullet = new Bullet();
		bullet.Init(100, 200);
		
		bullet.update(1.0); // deltaTime = 1 second
		
		assertEquals(200 - bullet.getSpeed(), bullet.getY());
		assertEquals(Bullet.MAX_RANGE - bullet.getSpeed(), bullet.getRange());
	}

	@Test
	void testBulletSetActive() {
		Bullet bullet = new Bullet();
		
		bullet.setActive(true);
		assertTrue(bullet.isActive());
		
		bullet.setActive(false);
		assertFalse(bullet.isActive());
	}

	@Test
	void testBulletTakeDamage() {
		Bullet bullet = new Bullet();
		
		bullet.takeDamage(10);
		assertFalse(bullet.isActive());
	}

}


