package service;

import org.springframework.stereotype.Service;

import entity.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductoService {

    private final List<Producto> productos = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);


    public Producto crearProducto(Producto producto) {
        producto.setId(idGenerator.getAndIncrement());
        productos.add(producto);
        return producto;
    }

   
    public List<Producto> listarProductos() {
        return productos;
    }

   
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productos.stream().filter(producto -> producto.getId().equals(id)).findFirst();
    }

  
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Optional<Producto> productoOpt = obtenerProductoPorId(id);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setStock(productoActualizado.getStock());
            return producto;
        }
        return null;
    }


    public boolean eliminarProducto(Long id) {
        return productos.removeIf(producto -> producto.getId().equals(id));
    }
}