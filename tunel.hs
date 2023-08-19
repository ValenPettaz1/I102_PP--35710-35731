module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun 

getLinksT :: Tunel -> [Link]
getLinksT (Tun link) = getCitiesL link

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 tunel = getLinksT `elem` getLinksT tunel

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link tunel = link `elem` getLinksT


--delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
--delayT tunel = getLinksT tunel

