package com.david;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
* 读写锁用于读多写少，性能很好
* 1)允许多个线程同时读共享变量
* 2)只允许一个线程写共享变量
* 3)写锁是互斥的，读锁不互斥
* */
public class ReadWriteLockDemo {
    class Cache<K, V> {
        final Map<K, V> m = new HashMap<K, V>();
        final ReadWriteLock rw1 = new ReentrantReadWriteLock();

        final Lock r = rw1.readLock();
        final Lock w = rw1.writeLock();

        //读缓存一次性写入
        V get(K key) {
            r.lock();
            try {
                return m.get(key);
            } finally {
                r.unlock();
            }
        }

        //读缓存，按需写入
        V lazyGet(K key) {
            V v = null;
            r.lock();
            try {
                v = m.get(key);
            } finally {
                r.unlock();
            }

            //缓存中存在，返回
            if (v != null) {
                return v;
            }

            //缓存中不存在，查询数据库
            w.lock();
            try {
                v = m.get(key);
                if (v == null) {
                    m.put(key, v);
                }
            } finally {
                w.unlock();
            }
            return v;
        }

        //写缓存
        V put(K key, V value) {
            w.lock();
            try {
                return m.put(key, value);
            } finally {
                w.unlock();
            }
        }
    }

    //关于读写锁有个规则就是，读锁不可以升级为写锁，但是写锁可以降级为读锁
    class CacheData {
        Object data;
        volatile boolean cacheValid;
        final ReadWriteLock rw1 = new ReentrantReadWriteLock();

        final Lock r = rw1.readLock();
        final Lock w = rw1.writeLock();

        void processCachedData() {
            r.lock();
            if (!cacheValid) {
                //释放读锁，因为不允许读锁的升级
                r.unlock();
                w.lock();
                try {
                    if (!cacheValid) {
                        //data =
                        cacheValid = true;
                    }
                    //释放写锁前，降级为读锁，降级是可以的
                    r.lock();
                } finally {
                    w.unlock();
                }
            }

            //此处仍然持有读锁
            try {
                //use(data);
            } finally {
                r.unlock();
            }
        }
    }
}
