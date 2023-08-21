module Point ( Point, newP, difP) 
    where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP = Poi

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi x1 y1) (Poi x2 y2) = sqrt (fromIntegral(x1-x2) ** 2 + fromIntegral(y1-y2) ** 2)

point1 = newP 100 250
point2 = newP (-150) 250


testP = [difP point1 point2 == 250.0,
        difP point2 point1 == 250.0,
        True]

