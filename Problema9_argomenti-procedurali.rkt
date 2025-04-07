;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname argomenti-procedurali) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define succ ;val int
  (lambda (x) ;rule: int
    (+ x 1)
    ))

(define add ;val int
  (lambda (x y)
    (if (= y 0)
        x
        (succ (add x (- y 1)))
        )
    ))

(define mul
  (lambda (x y)
    (if (= y 0)
        0
        (add x (mul x (- y 1)))
        )
    ))

(define pow
  (lambda (x y)
    (if (= y 0)
        1
        (mul x (pow x (- y 1)))
        )
    ))

(define h
  (lambda (f g) ;f, g: procedure
    (lambda (m n) ;m, n: interi
      (if (= n 0)
          (f m)
          (g m ((h f g) m (- n 1)))
          )
      )))
    
(define s2
  (lambda (u v)
    (+ v 1)
    ))


(define add2
      (h (lambda (i) i) s2)
    )

(define mul2
  (h (lambda (z) 0) add2)
    )

(define pow2
  (h (lambda (u) 1) mul2)
    )