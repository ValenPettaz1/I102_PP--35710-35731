module Quality ( Quality, newQ, capacityQ, delayQ)
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ = Qua

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ capacity _) = capacity

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ delay) = delay

quality1 = newQ "Baja" 3 0.1
quality2 = newQ "Media" 7 0.05
quality3 = newQ "Alta" 10 0.01


testQ = [capacityQ quality1 == 3,
        delayQ quality1 == 0.1, 
        True] 
