module Quality ( Quality, newQ, capacityQ, delayQ)
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ name capacity delay 
   | capacity > 0 && delay > 0 && name /= "" = Qua name capacity delay
   | capacity <= 0 = error "La capacidad debe ser mayor a 0"
   | delay <= 0 = error "El delay debe ser mayor a 0"
   | name == "" = error "Debe poner un nombre"
   | otherwise = error "Calidad inválida"

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ capacity _) = capacity

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ delay) = delay