module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where

import Point ( newP )
import Quality ( newQ )
import Link ( Link, newL, connectsL, delayL )
import City ( City, newC )

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

getLinksT :: Tunel -> [Link]
getLinksT (Tun link) = link

linksUsedByCity :: City -> Tunel -> [Link]
linksUsedByCity city tunel = [link | link <- linklist, connectsL city link] where
    linklist = getLinksT tunel

extremeCities :: City -> Tunel -> Bool
extremeCities city tunel 
    | length (linksUsedByCity city tunel) == 1 = True
    | otherwise = False

connectsT :: City -> City -> Tunel -> Bool -- indica si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 tunel = extremeCities city1 tunel && extremeCities city2 tunel 

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link tunel = link `elem` getLinksT tunel

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT tunel = sum (map delayL (getLinksT tunel))