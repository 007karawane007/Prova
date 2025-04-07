;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema6_tiles) (read-case-sensitive #t) (teachpacks ((lib "Teachpack_problema6.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "Teachpack_problema6.ss" "installed-teachpacks")) #f)))
(set-tessellation-shift-step!)

(define L-tasselation ;val: immagine
  (lambda (n) ;n: intero
    (cond ((= n 1)
            L-tile)
          ((= n 2)
           (glue-tiles (shift-right (glue-tiles (shift-down L-tile 1) (shift-right (quarter-turn-right L-tile) 1)) 1) (glue-tiles L-tile (shift-down (quarter-turn-left L-tile) 2)))
           )
          (else
           (ta (L-tasselation (quotient n 2)) n)
          )
          )
    ))

(define ta
  (lambda (img n)
    (glue-tiles (shift-right (glue-tiles (shift-down img (quotient n 2)) (shift-right (quarter-turn-right img) (quotient n 2))) (quotient n 2)) (glue-tiles img (shift-down (quarter-turn-left img) n)))
    ))

;(L-tasselation 16)
