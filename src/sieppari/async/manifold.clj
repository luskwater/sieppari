(ns sieppari.async.manifold
  (:require [sieppari.async :as sa]
            [manifold.deferred :as d]))

(extend-protocol sa/AsyncContext
  manifold.deferred.Deferred
  (async? [_] true)
  (continue [d f] (d/chain'- nil d f))
  (await [d] (deref d)))
